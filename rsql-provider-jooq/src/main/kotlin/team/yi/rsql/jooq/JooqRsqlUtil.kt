package team.yi.rsql.jooq

import org.jooq.Field
import org.jooq.impl.*
import team.yi.rsql.RsqlTypeConstants

object JooqRsqlUtil {
    fun toValue(argument: String, typePrompt: String?): Field<out Any> {
        return when (typePrompt) {
            RsqlTypeConstants.RAW -> DSL.field(DSL.raw(argument))
            RsqlTypeConstants.NUMBER -> DSL.value(argument, SQLDataType.NUMERIC)
            else -> DSL.value(argument)
        }
    }
}
