package team.yi.rsql

import cz.jirutka.rsql.parser.ast.ComparisonNode
import io.github.oshai.kotlinlogging.KotlinLogging
import team.yi.rsql.core.*

class RsqlBuilder<R>(
    private val transformers: Map<String, RsqlTransformer<R>>,
    private val visitCallback: RsqlVisitCallback<R>,
) {
    private val kLogger = KotlinLogging.logger {}

    fun visit(rsqlNode: ComparisonNode, rsqlOperator: RsqlOperator): RsqlExpression<R>? {
        val selector = rsqlNode.selector
        val arguments = rsqlNode.arguments

        if (selector.isBlank()) {
            kLogger.trace { "selector of node is blank, skip.)" }

            return null
        }

        val typePrompt = detectTypePrompt(selector)
        val fieldPath = if (typePrompt == null) {
            selector
        } else {
            selector.substringBefore(RsqlConstants.TYPE_PROMPT_DELIMITER)
        }

        val transformer = transformers.getValue(rsqlOperator.symbol)

        val result = synchronized(transformer) {
            if (!transformer.supports(rsqlNode, rsqlOperator)) {
                kLogger.trace { "not support this transformer(${transformer.javaClass.name})." }

                return null
            }

            transformer.transform(fieldPath, arguments, typePrompt)
        }

        if (result == null) {
            kLogger.trace { "rsql node transform failed." }

            return null
        } else {
            visitCallback.afterTransform(result)

            kLogger.trace { "rsql node transform success: $result}" }
        }

        return RsqlExpression(result.result)
    }

    fun combineLogical(logicalType: LogicalType, predicate: RsqlExpression<R>, subPredicate: RsqlExpression<R>): RsqlLogicalExpression<R> {
        return visitCallback.combineLogical(logicalType, predicate, subPredicate)
    }

    private fun detectTypePrompt(selector: String): String? {
        return selector.indexOf(RsqlConstants.TYPE_PROMPT_DELIMITER).let { index ->
            if (index > 0) {
                selector.substring(index + 1).trim().ifEmpty { null }
            } else {
                null
            }
        }
    }
}
