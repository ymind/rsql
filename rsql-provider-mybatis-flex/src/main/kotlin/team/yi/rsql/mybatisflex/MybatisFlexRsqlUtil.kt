package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.*
import team.yi.rsql.*
import java.text.NumberFormat

object MybatisFlexRsqlUtil {
    private val numberFormat = NumberFormat.getInstance()

    fun toValue(argument: String, typePrompt: String?, quote: Boolean = false): Any {
        val quoted = argument.sqlEscape().let {
            if (quote) {
                "'$argument'"
            } else {
                argument
            }
        }

        return when (typePrompt) {
            RsqlConstants.TYPE_PROMPT_RAW -> RawQueryColumn(quoted)
            RsqlConstants.TYPE_PROMPT_NUMBER -> toNumber(argument)
            RsqlConstants.TYPE_PROMPT_BOOLEAN -> argument.toBoolean()
            // RsqlConstants.TYPE_PROMPT_DATE, RsqlConstants.TYPE_PROMPT_TIME, RsqlConstants.TYPE_PROMPT_DATETIME -> RawQueryColumn(quoted)
            else -> RawQueryColumn(quoted)
        }
    }

    fun toNumber(argument: String): Number {
        return numberFormat.parse(argument)
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
