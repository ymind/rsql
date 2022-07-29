package team.yi.rsql.core

interface RsqlVisitCallback<R> {
    fun beforeTransform(selector: String, arguments: List<String>, rsqlOperator: RsqlOperator) = Unit

    fun afterTransform(queryPart: RsqlQueryPart<R>)

    fun combineLogical(logicalType: LogicalType, predicate: RsqlExpression<R>, subPredicate: RsqlExpression<R>): RsqlLogicalExpression<R>
}
