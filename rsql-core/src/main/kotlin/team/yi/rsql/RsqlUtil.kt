package team.yi.rsql

import cz.jirutka.rsql.parser.ast.ComparisonOperator
import team.yi.rsql.core.*

object RsqlUtil {
    fun getOperators(customOperators: Collection<RsqlOperator>? = null): Set<ComparisonOperator> {
        val operators = Operator.entries
            .map { ComparisonOperator(it.symbols, it.multiValue) }
            .toMutableSet()

        if (customOperators.isNullOrEmpty()) return operators

        customOperators.forEach { operator ->
            operator.symbols.mapTo(operators) {
                runCatching {
                    ComparisonOperator(it, operator.multiValue)
                }.onFailure { ex ->
                    throw IllegalArgumentException("Invalid operator symbol: '$it' Operator ${ex.message}", ex)
                }.getOrThrow()
            }
        }

        return operators
    }

    const val TYPE_PROMPT_DELIMITER = "@"
}

fun String.sqlEscape(): String = this.replace("'", "''")
