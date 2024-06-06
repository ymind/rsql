package team.yi.rsql.core

import cz.jirutka.rsql.parser.ast.Arity
import java.io.*

class RsqlOperator(
    val symbols: List<String>,
    val arity: Arity,
) : Serializable {
    val symbol: String = symbols[0]

    override fun toString(): String {
        return "symbols: [${symbols.joinToString()}], arity: $arity"
    }

    companion object {
        @Serial
        private const val serialVersionUID = 1L

        fun from(operator: Operator): RsqlOperator {
            return RsqlOperator(operator.symbols, arity = operator.arity)
        }
    }
}
