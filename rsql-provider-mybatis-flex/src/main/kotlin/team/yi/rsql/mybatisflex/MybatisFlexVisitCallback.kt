package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.QueryCondition
import team.yi.rsql.core.*

class MybatisFlexVisitCallback(
    private val transformResults: MutableList<RsqlQueryPart<QueryCondition>>,
) : RsqlVisitCallback<QueryCondition> {
    override fun afterTransform(queryPart: RsqlQueryPart<QueryCondition>) {
        transformResults.add(queryPart)
    }

    override fun combineLogical(
        logicalType: LogicalType,
        predicate: RsqlExpression<QueryCondition>,
        subPredicate: RsqlExpression<QueryCondition>,
    ): RsqlLogicalExpression<QueryCondition> {
        val left = predicate.value
        val right = subPredicate.value

        val condition = QueryCondition.createEmpty().and(left)
        val queryBlock = if (logicalType == LogicalType.OR) {
            condition.or(right)
        } else {
            condition.and(right)
        }

        return RsqlLogicalExpression(logicalType, queryBlock)
    }
}
