package team.yi.rsql.core

import java.io.*

class RsqlQueryPart<R>(
    val selector: String,
    val arguments: List<String>,
    val result: R,
    val useRawValue: Boolean,
    val parameters: List<Any?> = emptyList(),
) : Serializable {
    constructor(
        selector: String,
        arguments: List<String>,
        result: R,
        useRawValue: Boolean,
        vararg parameters: Any?,
    ) : this(selector, arguments, result, useRawValue, parameters.toList())

    override fun toString(): String {
        return buildString {
            append("selector = `$selector`, ")
            append("arguments = `${arguments.joinToString()}`, ")
            append("result = `$result`; ")
            append("useRawValue = `$useRawValue`; ")

            if (parameters.isNotEmpty()) {
                append("`${parameters.joinToString { it.toString() }}`")
            }
        }
    }

    companion object {
        @Serial
        private const val serialVersionUID = 1L
    }
}
