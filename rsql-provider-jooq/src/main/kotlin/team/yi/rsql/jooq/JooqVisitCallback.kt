package team.yi.rsql.jooq

import org.jooq.*
import org.jooq.impl.DSL
import team.yi.rsql.core.*

class JooqVisitCallback(
    private val transformResults: MutableList<RsqlQueryPart<QueryPart>>,
) : RsqlVisitCallback<QueryPart> {
    override fun afterTransform(queryPart: RsqlQueryPart<QueryPart>) {
        transformResults.add(queryPart)
    }

    override fun combineLogical(
        logicalType: LogicalType,
        predicate: RsqlExpression<QueryPart>,
        subPredicate: RsqlExpression<QueryPart>,
    ): RsqlLogicalExpression<QueryPart> {
        val left = predicate.value as? Condition
        val right = subPredicate.value as? Condition

        val queryPart = if (logicalType == LogicalType.OR) {
            DSL.or(left, right)
        } else {
            DSL.and(left, right)
        }

        return RsqlLogicalExpression(logicalType, queryPart)
    }
}
