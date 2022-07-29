package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.*

object MybatisFlexRsqlUtil {
    fun toValue(argument: String, typePrompt: String?, quote: Boolean = false): QueryColumn {
        val quoted = if (quote) {
            "'$argument'"
        } else {
            argument
        }

        return when (typePrompt) {
            "raw" -> RawQueryColumn(quoted)
            "num" -> ArithmeticQueryColumn(argument)
            else -> RawQueryColumn(quoted)
        }
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
