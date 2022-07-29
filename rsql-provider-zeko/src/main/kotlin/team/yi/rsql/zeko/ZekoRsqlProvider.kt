package team.yi.rsql.zeko

import io.zeko.db.sql.*
import io.zeko.db.sql.extensions.common.distinct
import team.yi.rsql.*
import team.yi.rsql.core.RsqlQueryPart

class ZekoRsqlProvider(
    config: ZekoRsqlConfig,
) : RsqlProvider<Query, QueryParts, QueryBlock>(config) {
    override fun build(input: RsqlInput): RsqlOutput<Query, QueryParts, QueryBlock> {
        val query = Query(espTableName = false)
        query.fields(*input.fields?.toTypedArray() ?: arrayOf("*"))

        input.distinct?.also { if (it) query.distinct() }
        input.from?.also { query.from(it) }

        val operatorTransformResults = mutableListOf<RsqlQueryPart<QueryBlock>>()
        val visitCallback = ZekoVisitCallback(operatorTransformResults)
        val where = parse(input.where, visitCallback)

        where?.also { query.where(it) }

        val groupBy = input.groupBy

        if (!groupBy.isNullOrEmpty()) {
            query.groupBy(groupBy)

            val having = parse(input.having, visitCallback)

            having?.also { query.having(it) }
        }

        input.orderBy?.forEach {
            val expr = it.substringBefore(' ').trim()
            val order = it.substringAfter(expr).trim()

            if (order.startsWith('D') || order.startsWith('d')) {
                query.orderDesc(expr)
            } else {
                query.orderAsc(expr)
            }
        }
        input.limit?.also { limit ->
            val offset = input.offset?.toInt() ?: 0

            query.limit(limit.toInt(), offset)
        }

        // `countQuery` MUST need the `table`, noop when groupBy
        val countQueryParts = buildCountQueryParts(query)
        val countQuery = countQueryParts

        return RsqlOutput(query, countQuery, operatorTransformResults)
    }

    private fun buildCountQueryParts(query: Query): QueryParts {
        val parts = query.toParts()
        val custom = parts.custom.clone().apply {
            this.remove(CustomPart.SELECT)
            this.remove(CustomPart.FIELD)
            this.remove(CustomPart.ORDER)
            this.remove(CustomPart.LIMIT)
        }

        val countFields = LinkedHashMap<String, Array<String>>()
        val q = Query().fields("COUNT(*) __count")

        return QueryParts(
            q,
            countFields,
            parts.from,
            parts.joins,
            parts.where,
            emptyList(),
            null,
            parts.groupBys,
            parts.havings,
            custom
        )
    }
}
