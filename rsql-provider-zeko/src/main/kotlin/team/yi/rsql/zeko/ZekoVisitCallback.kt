package team.yi.rsql.zeko

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.core.*

class ZekoVisitCallback(
    private val transformResults: MutableList<RsqlQueryPart<QueryBlock>>,
) : RsqlVisitCallback<QueryBlock> {
    override fun afterTransform(queryPart: RsqlQueryPart<QueryBlock>) {
        transformResults.add(queryPart)
    }

    override fun combineLogical(
        logicalType: LogicalType,
        predicate: RsqlExpression<QueryBlock>,
        subPredicate: RsqlExpression<QueryBlock>,
    ): RsqlLogicalExpression<QueryBlock> {
        val left = predicate.value
        val right = subPredicate.value

        val queryBlock = if (logicalType == LogicalType.OR) {
            QueryBlock("($left", " OR ", "$right)")
        } else {
            QueryBlock("$left", " AND ", "$right")
        }

        return RsqlLogicalExpression(logicalType, queryBlock)
    }
}
