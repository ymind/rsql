package team.yi.rsql

import cz.jirutka.rsql.parser.ast.ComparisonOperator
import team.yi.rsql.core.RsqlOperator

open class RsqlConfig<R> {
    private val transformersMap = mutableMapOf<String, RsqlTransformer<R>>()
    private val operators = mutableSetOf<RsqlOperator>()

    val comparisonOperators: Set<ComparisonOperator> = RsqlUtil.getOperators(operators)

    val transformers: Map<String, RsqlTransformer<R>>
        get() = transformersMap

    fun register(vararg operators: RsqlOperator) {
        this.operators.addAll(operators)
    }

    fun register(operator: RsqlOperator) {
        this.operators.add(operator)
    }

    fun register(transformer: RsqlTransformer<R>, symbol: String) {
        transformersMap.putIfAbsent(symbol, transformer)
    }

    fun register(transformer: RsqlTransformer<R>) {
        register(transformer, transformer.operator.symbols)
    }

    fun register(transformer: RsqlTransformer<R>, symbols: Array<out String>) {
        symbols.distinct().forEach {
            register(transformer, it)
        }
    }
}
