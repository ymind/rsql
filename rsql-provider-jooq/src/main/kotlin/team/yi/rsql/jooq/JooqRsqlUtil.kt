package team.yi.rsql.jooq

import org.jooq.Field
import org.jooq.impl.*

object JooqRsqlUtil {
    fun toValue(argument: String, typePrompt: String?): Field<out Any> {
        return when (typePrompt) {
            "raw" -> DSL.field(DSL.raw(argument))
            "num" -> DSL.value(argument, SQLDataType.NUMERIC)
            else -> DSL.value(argument)
        }
    }
}
