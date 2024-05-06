package team.yi.rsql

import io.github.oshai.kotlinlogging.KotlinLogging
import team.yi.rsql.core.*

class RsqlBuilder<R>(
    private val transformers: Map<String, RsqlTransformer<R>>,
    private val visitCallback: RsqlVisitCallback<R>,
) {
    private val kLogger = KotlinLogging.logger {}

    fun visit(selector: String, arguments: List<String>, rsqlOperator: RsqlOperator): RsqlExpression<R>? {
        if (selector.isBlank()) {
            kLogger.trace { "selector of node is blank, skip.)" }

            return null
        }

        val typePrompt = detectTypePrompt(selector)
        val fieldPath = if (typePrompt == null) {
            selector
        } else {
            selector.substringBefore(RsqlTypeConstants.TYPE_PROMPT_DELIMITER)
        }

        visitCallback.beforeTransform(fieldPath, arguments, rsqlOperator)

        val transformer = transformers.getValue(rsqlOperator.symbol)
        val result = transformer.transform(fieldPath, arguments, typePrompt)

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
        return selector.indexOf(RsqlTypeConstants.TYPE_PROMPT_DELIMITER).let { index ->
            if (index > 0) {
                selector.substring(index + 1).trim().ifEmpty { null }
            } else {
                null
            }
        }
    }
}
