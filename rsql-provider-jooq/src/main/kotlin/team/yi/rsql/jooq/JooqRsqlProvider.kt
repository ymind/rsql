package team.yi.rsql.jooq

import org.jooq.*
import org.jooq.impl.DSL
import team.yi.rsql.*
import team.yi.rsql.core.RsqlQueryPart

class JooqRsqlProvider(
    private val dslContext: DSLContext,
    config: JooqRsqlConfig,
) : RsqlProvider<Query, Query, QueryPart>(config) {
    override fun build(input: RsqlInput): RsqlOutput<Query, Query, QueryPart> {
        val fields = input.fields.orEmpty().map {
            val fieldStr = it.trim()

            if (fieldStr == "*") {
                DSL.asterisk()
            } else {
                val fieldData = fieldStr.split(" AS ", " as ", " As ", " aS ")

                if (fieldData.size >= 2) {
                    DSL.field(fieldData[0]).`as`(fieldData[1])
                } else {
                    DSL.field(fieldData[0])
                }
            }
        }

        val query = if (input.distinct == true) {
            dslContext.selectDistinct(fields)
        } else {
            dslContext.select(fields)
        }

        val table = input.from?.let { DSL.table(it) }?.also {
            query.from(it)
        }

        val operatorTransformResults = mutableListOf<RsqlQueryPart<QueryPart>>()
        val visitCallback = JooqVisitCallback(operatorTransformResults)

        val where = parse(input.where, visitCallback) as? Condition
        val groupByFields = input.groupBy?.map { DSL.field(it) }

        where?.also { query.where(it) }

        if (!groupByFields.isNullOrEmpty()) {
            query.groupBy(groupByFields)

            val having = parse(input.having, visitCallback) as? Condition

            having?.also { query.having(it) }
        }

        // `countQuery` MUST need the `table`, noop when groupBy
        val countQuery = table?.let {
            if (groupByFields.isNullOrEmpty()) {
                dslContext.dsl()
                    .selectCount()
                    .from(table)
                    .apply {
                        where?.also { this.where(where) }
                    }
            } else {
                null
            }
        }

        val orderByFields = input.orderBy.orEmpty()
            .map {
                val expr = it.substringBefore(' ').trim()
                val order = it.substringAfter(expr).trim()

                if (order.startsWith('D') || order.startsWith('d')) {
                    DSL.field(expr).desc()
                } else {
                    DSL.field(expr).asc()
                }
            }

        if (orderByFields.isNotEmpty()) query.orderBy(orderByFields)

        val limit = input.limit

        if (limit != null && limit > 0) {
            val offset = input.offset?.toInt() ?: 0

            if (offset < 1) {
                query.limit(limit)
            } else {
                query.limit(offset, limit)
            }
        }

        return RsqlOutput(query, countQuery, operatorTransformResults)
    }
}
