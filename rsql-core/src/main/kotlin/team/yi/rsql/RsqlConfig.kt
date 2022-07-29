package team.yi.rsql

import cz.jirutka.rsql.parser.ast.ComparisonOperator
import team.yi.rsql.core.*

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

    fun register(transformer: RsqlTransformer<R>, vararg symbols: String) {
        symbols.distinct().forEach {
            transformersMap.putIfAbsent(it, transformer)
        }
    }

    fun register(transformer: RsqlTransformer<R>, symbol: String) {
        transformersMap.putIfAbsent(symbol, transformer)
    }

    fun register(transformer: RsqlTransformer<R>, operator: Operator) {
        register(transformer, *operator.symbols)
    }

    fun register(transformer: RsqlTransformer<R>, operator: RsqlOperator) {
        register(transformer, *operator.symbols)
    }
}
