package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.*
import team.yi.rsql.*
import team.yi.rsql.tools.KmDateUtil
import java.text.NumberFormat

private val numberFormat = NumberFormat.getInstance()

fun String.toNumber(): Number {
    return numberFormat.parse(this)
}

fun convertType(argument: String, typePrompt: String?): Any? {
    return when (typePrompt) {
        RsqlConstants.TYPE_PROMPT_NUMBER -> argument.toNumber()
        RsqlConstants.TYPE_PROMPT_BOOLEAN -> argument.toBoolean()
        RsqlConstants.TYPE_PROMPT_DATE -> KmDateUtil.parse(argument)
        RsqlConstants.TYPE_PROMPT_DATETIME -> KmDateUtil.parse(argument)
        RsqlConstants.TYPE_PROMPT_OFFSET_DATE -> KmDateUtil.parseOffsetDateTime(argument)
        RsqlConstants.TYPE_PROMPT_OFFSET_DATETIME -> KmDateUtil.parseOffsetDateTime(argument)

        else -> argument.sqlEscape()
    }
}

fun toQueryColumn(argument: String, typePrompt: String?): RawQueryColumn {
    val quoted = argument.sqlEscape().let { "'$argument'" }

    return when (typePrompt) {
        RsqlConstants.TYPE_PROMPT_NUMBER, RsqlConstants.TYPE_PROMPT_BOOLEAN -> RawQueryColumn(argument)
        else -> RawQueryColumn(quoted)
    }
}

fun QueryColumn.likeRegex(value: Any?, isEffective: Boolean = true): QueryCondition {
    return if (QueryColumnBehavior.getIgnoreFunction().test(value)) {
        QueryCondition.createEmpty()
    } else {
        QueryCondition.create(this, " REGEX_LIKE ", value).`when`(isEffective)
    }
}

fun QueryColumn.notLikeRegex(value: Any?, isEffective: Boolean = true): QueryCondition {
    return if (QueryColumnBehavior.getIgnoreFunction().test(value)) {
        QueryCondition.createEmpty()
    } else {
        QueryCondition.create(this, " NOT REGEX_LIKE ", value).`when`(isEffective)
    }
}
