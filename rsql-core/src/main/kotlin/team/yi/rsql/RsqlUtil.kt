package team.yi.rsql

import cz.jirutka.rsql.parser.ast.ComparisonOperator
import team.yi.rsql.core.*

object RsqlUtil {
    fun getOperators(customOperators: Collection<RsqlOperator>? = null): Set<ComparisonOperator> {
        val operators = Operator.entries
            .map {
                ComparisonOperator(it.symbols.toTypedArray(), it.arity)
            }
            .toMutableSet()

        if (customOperators.isNullOrEmpty()) return operators

        customOperators.forEach { operator ->
            operator.symbols.mapTo(operators) {
                runCatching {
                    ComparisonOperator(it, operator.arity)
                }.onFailure { ex ->
                    throw IllegalArgumentException("Invalid operator symbol: '$it' Operator ${ex.message}", ex)
                }.getOrThrow()
            }
        }

        return operators
    }
}

fun String.sqlEscape(): String = this.replace("'", "''")
