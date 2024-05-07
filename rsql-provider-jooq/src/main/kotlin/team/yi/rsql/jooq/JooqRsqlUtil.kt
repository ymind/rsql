package team.yi.rsql.jooq

import org.jooq.Field
import org.jooq.impl.*
import team.yi.rsql.RsqlConstants

object JooqRsqlUtil {
    fun toValue(argument: String, typePrompt: String?): Field<out Any> {
        return when (typePrompt) {
            RsqlConstants.TYPE_PROMPT_RAW -> DSL.field(DSL.raw(argument))
            RsqlConstants.TYPE_PROMPT_NUMBER -> DSL.value(argument, SQLDataType.NUMERIC)
            else -> DSL.value(argument)
        }
    }
}
