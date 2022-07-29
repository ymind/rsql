package team.yi.rsql.core

import java.io.*

open class RsqlExpression<R>(open val value: R) : Serializable {
    override fun toString(): String = value?.toString() ?: ""

    companion object {
        @Serial
        private const val serialVersionUID = 1L
    }
}
