package team.yi.rsql

import cz.jirutka.rsql.parser.RSQLParser
import io.github.oshai.kotlinlogging.KotlinLogging
import team.yi.rsql.core.RsqlVisitCallback

abstract class RsqlProvider<Q, C, R>(
    private val config: RsqlConfig<R>,
) {
    private val kLogger = KotlinLogging.logger {}

    abstract fun build(input: RsqlInput): RsqlOutput<Q, C, R>

    protected fun parse(rsql: String?, visitCallback: RsqlVisitCallback<R>): R? {
        if (rsql.isNullOrBlank()) {
            kLogger.trace { "parameter `rsql` is null or blank, exit." }

            return null
        }

        val rsqlBuilder = RsqlBuilder(config.transformers, visitCallback)
        val visitor = RsqlVisitor(rsqlBuilder, config.rsqlNodeValidator)

        val rootNode = RSQLParser(config.comparisonOperators).parse(rsql)

        return rootNode.accept(visitor)?.value
    }

    init {
        kLogger.trace {
            buildString {
                appendLine("team.yi.rsql.Rsql init with:")

                appendLine("  transformers:")

                config.transformers.forEach {
                    appendLine("  - ${it.key}: ${it.value.javaClass.name}")
                }

                appendLine("  comparison operators:")

                config.comparisonOperators.forEach {
                    appendLine("  - ${it.symbols.joinToString()}: [${it.arity.min()}, ${it.arity.max()}]")
                }
            }
        }
    }
}
