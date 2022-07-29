package team.yi.rsql.zeko.transformer.aggregations

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.aggregations.agg
import team.yi.rsql.core.*
import team.yi.rsql.zeko.transformer.ZekoRsqlTransformer

abstract class ZekoAggregationTransformer(
    private val sqlAggregation: SqlAggregation,
    private val operator: String,
) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = agg(sqlAggregation.functionName, selector, operator, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
