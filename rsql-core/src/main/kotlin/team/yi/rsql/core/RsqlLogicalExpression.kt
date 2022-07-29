package team.yi.rsql.core

import java.io.Serial

open class RsqlLogicalExpression<R>(
    val logicalType: LogicalType,
    override val value: R,
) : RsqlExpression<R>(value) {
    override fun toString(): String {
        return buildString {
            append("logicalType = `$logicalType`, ")
            append("value = `$value`")
        }
    }

    companion object {
        @Serial
        private const val serialVersionUID = 1L
    }
}
