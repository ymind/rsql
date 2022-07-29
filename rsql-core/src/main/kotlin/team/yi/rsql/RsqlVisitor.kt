package team.yi.rsql

import cz.jirutka.rsql.parser.ast.*
import team.yi.rsql.core.*

class RsqlVisitor<R>(
    private val rsqlBuilder: RsqlBuilder<R>,
) : RSQLVisitor<RsqlExpression<R>, RsqlExpression<R>> {
    override fun visit(node: AndNode?, param: RsqlExpression<R>?): RsqlExpression<R>? {
        return logicalExpression(node, param, LogicalType.AND)
    }

    override fun visit(node: OrNode?, param: RsqlExpression<R>?): RsqlExpression<R>? {
        return logicalExpression(node, param, LogicalType.OR)
    }

    override fun visit(node: ComparisonNode?, param: RsqlExpression<R>?): RsqlExpression<R>? {
        if (node?.operator == null) return null

        val rsqlOperator = RsqlOperator(*node.operator.symbols, multiValue = node.operator.isMultiValue)

        return rsqlBuilder.visit(node.selector, node.arguments, rsqlOperator)
    }

    private fun logicalExpression(node: LogicalNode?, param: RsqlExpression<R>?, logicalType: LogicalType): RsqlExpression<R>? {
        if (node?.operator == null) return null

        val children = node.children.toMutableList()
        val firstNode = children.removeAt(0)
        var predicate = firstNode.accept(this, param) as RsqlExpression

        for (subNode in children) {
            val subPredicate = subNode.accept(this, param) as RsqlExpression

            predicate = combineLogicalExpression(logicalType, predicate, subPredicate)
        }

        return predicate
    }

    private fun combineLogicalExpression(logicalType: LogicalType, predicate: RsqlExpression<R>, subPredicate: RsqlExpression<R>): RsqlExpression<R> {
        return rsqlBuilder.combineLogical(logicalType, predicate, subPredicate)
    }
}
