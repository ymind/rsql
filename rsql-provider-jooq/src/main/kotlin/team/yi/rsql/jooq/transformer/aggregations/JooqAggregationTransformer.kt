package team.yi.rsql.jooq.transformer.aggregations

import org.jooq.*
import org.jooq.impl.*
import team.yi.rsql.core.*
import team.yi.rsql.core.Operator
import team.yi.rsql.jooq.transformer.JooqRsqlTransformer

abstract class JooqAggregationTransformer(
    private val sqlAggregation: SqlAggregation,
    private val aggOperator: String,
    override val operator: Operator,
) : JooqRsqlTransformer(operator) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart>? {
        val field = DSL.field(selector, SQLDataType.NUMERIC.type)
        val value1 = DSL.value(arguments[0], SQLDataType.NUMERIC)

        val queryPart = when (sqlAggregation) {
            SqlAggregation.AVG -> aggregate(DSL.avg(field), aggOperator, value1)
            SqlAggregation.COUNT -> aggregate(DSL.count(field), aggOperator, DSL.value(arguments[0].toIntOrNull()))
            SqlAggregation.MAX -> aggregate(DSL.max(field), aggOperator, value1)
            SqlAggregation.MIN -> aggregate(DSL.min(field), aggOperator, value1)
            SqlAggregation.SUM -> aggregate(DSL.sum(field), aggOperator, value1)
        }

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }

    private fun <T> aggregate(fn: AggregateFunction<T>, operator: String, value: Param<T>): QueryPart {
        return when (operator) {
            ">" -> fn.gt(value)
            ">=" -> fn.ge(value)
            "<" -> fn.lt(value)
            "<=" -> fn.le(value)
            else -> fn.eq(value)
        }
    }
}
