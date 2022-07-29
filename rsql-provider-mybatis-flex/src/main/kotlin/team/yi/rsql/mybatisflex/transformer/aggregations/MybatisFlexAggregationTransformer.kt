package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import com.mybatisflex.core.query.*
import team.yi.rsql.core.*
import team.yi.rsql.mybatisflex.transformer.MybatisFlexRsqlTransformer

abstract class MybatisFlexAggregationTransformer(
    private val sqlAggregation: SqlAggregation,
    private val operator: SqlOperator,
) : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = arguments[0]
        val queryBlock = QueryCondition.create(
            FunctionQueryColumn(sqlAggregation.functionName, field),
            operator,
            value1,
        )

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
