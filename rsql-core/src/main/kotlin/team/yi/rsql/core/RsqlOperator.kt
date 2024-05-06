package team.yi.rsql.core

import cz.jirutka.rsql.parser.ast.Arity
import java.io.*

class RsqlOperator(
    vararg val symbols: String,
    val arity: Arity,
) : Serializable {
    val symbol: String = symbols[0]

    override fun hashCode(): Int = symbols.contentHashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        return when (other) {
            is Operator -> other.symbols.equalsTo(symbols)
            is RsqlOperator -> other.symbols.equalsTo(symbols)
            else -> false
        }
    }

    override fun toString(): String {
        return "symbols: [${symbols.joinToString()}], arity: $arity"
    }

    private fun Array<out String>.equalsTo(symbols: Array<out String>): Boolean {
        return this.map { it.lowercase() }
            .intersect(symbols.map { it.lowercase() }.toSet())
            .isNotEmpty()
    }

    companion object {
        @Serial
        private const val serialVersionUID = 1L

        fun from(operator: Operator): RsqlOperator {
            return RsqlOperator(*operator.symbols, arity = operator.arity)
        }
    }
}
