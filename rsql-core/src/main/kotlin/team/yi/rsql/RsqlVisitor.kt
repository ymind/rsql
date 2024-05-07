package team.yi.rsql

import cz.jirutka.rsql.parser.ast.*
import team.yi.rsql.core.*

class RsqlVisitor<R>(
    private val rsqlBuilder: RsqlBuilder<R>,
    private val rsqlNodeValidator: RsqlNodeValidator?,
) : RSQLVisitor<RsqlExpression<R>, RsqlExpression<R>> {
    override fun visit(node: AndNode?, param: RsqlExpression<R>?): RsqlExpression<R>? {
        return logicalExpression(node, param, LogicalType.AND)
    }

    override fun visit(node: OrNode?, param: RsqlExpression<R>?): RsqlExpression<R>? {
        return logicalExpression(node, param, LogicalType.OR)
    }

    override fun visit(node: ComparisonNode?, param: RsqlExpression<R>?): RsqlExpression<R>? {
        if (node?.operator == null) return null

        val rsqlNode = if (rsqlNodeValidator == null) {
            node
        } else {
            rsqlNodeValidator.validate(node)
        }

        if (rsqlNode == null) return null

        val rsqlOperator = RsqlOperator(*rsqlNode.operator.symbols, arity = rsqlNode.operator.arity)

        return rsqlBuilder.visit(rsqlNode.selector, rsqlNode.arguments, rsqlOperator)
    }

    private fun logicalExpression(node: LogicalNode?, param: RsqlExpression<R>?, logicalType: LogicalType): RsqlExpression<R>? {
        if (node?.operator == null) return null

        val children = node.children.toMutableList()
        val firstNode = children.removeAt(0)
        var predicate: RsqlExpression<R>? = firstNode.accept(this, param)

        for (subNode in children) {
            val subPredicate = subNode.accept(this, param) ?: continue

            predicate = if (predicate == null) {
                subPredicate
            } else {
                combineLogicalExpression(logicalType, predicate, subPredicate)
            }
        }

        return predicate
    }

    private fun combineLogicalExpression(logicalType: LogicalType, predicate: RsqlExpression<R>, subPredicate: RsqlExpression<R>): RsqlExpression<R> {
        return rsqlBuilder.combineLogical(logicalType, predicate, subPredicate)
    }
}
