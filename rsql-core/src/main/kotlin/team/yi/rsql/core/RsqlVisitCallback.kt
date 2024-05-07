package team.yi.rsql.core

interface RsqlVisitCallback<R> {
    fun afterTransform(queryPart: RsqlQueryPart<R>)

    fun combineLogical(logicalType: LogicalType, predicate: RsqlExpression<R>, subPredicate: RsqlExpression<R>): RsqlLogicalExpression<R>
}
