package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.*
import team.yi.rsql.*
import team.yi.rsql.core.RsqlQueryPart

class MybatisFlexRsqlProvider(
    private val queryWrapper: QueryWrapper,
    config: MybatisFlexRsqlConfig,
) : RsqlProvider<QueryWrapper, QueryWrapper, QueryCondition>(config) {
    override fun build(input: RsqlInput): RsqlOutput<QueryWrapper, QueryWrapper, QueryCondition> {
        val columns = input.fields?.map {
            RawQueryColumn(it)
        }?.toTypedArray()

        if (columns.isNullOrEmpty()) {
            input.distinct?.also {
                queryWrapper.select(QueryMethods.distinct())
            }
        } else {
            if (input.distinct == true) {
                queryWrapper.select(QueryMethods.distinct(*columns))
            } else {
                queryWrapper.select(*columns)
            }
        }

        input.from?.also { from ->
            val tables = from.split(",").map { tableDef ->
                tableDef.trim().split(" ")
            }.map {
                if (it.size == 2) {
                    QueryTable(it[0]).`as`(it[1])
                } else {
                    QueryTable(it[0])
                }
            }.toTypedArray()

            queryWrapper.from(*tables)
        }

        val operatorTransformResults = mutableListOf<RsqlQueryPart<QueryCondition>>()
        val visitCallback = MybatisFlexVisitCallback(operatorTransformResults)
        val where = parse(input.where, visitCallback)

        where?.also {
            queryWrapper.where(it)
        }

        val groupBys = input.groupBy?.map { RawQueryColumn(it) }?.toTypedArray()

        if (!groupBys.isNullOrEmpty()) {
            queryWrapper.groupBy(*groupBys)

            val having = parse(input.having, visitCallback)

            having?.also { queryWrapper.having(it) }
        }

        input.orderBy?.forEach {
            val expr = it.substringBeforeLast(' ').trim()
            val order = it.substringAfterLast(' ').trim().firstOrNull() ?: 'a'

            if (order == 'D' || order == 'd') {
                queryWrapper.orderBy(expr, false)
            } else {
                queryWrapper.orderBy(expr, true)
            }
        }
        input.limit?.also { limit ->
            val offset = input.offset?.toInt() ?: 0

            queryWrapper.limit(offset, limit.toInt())
        }

        return RsqlOutput(queryWrapper, queryWrapper, operatorTransformResults)
    }
}
