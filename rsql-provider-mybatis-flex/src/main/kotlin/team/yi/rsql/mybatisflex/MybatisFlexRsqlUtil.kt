package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.*
import team.yi.rsql.*

object MybatisFlexRsqlUtil {
    fun toValue(argument: String, typePrompt: String?): QueryColumn {
        val quoted = argument.sqlEscape()

        return when (typePrompt) {
            RsqlTypeConstants.RAW -> RawQueryColumn(quoted)
            RsqlTypeConstants.NUMBER -> ArithmeticQueryColumn(argument)
            RsqlTypeConstants.BOOLEAN -> RawQueryColumn(argument.toBoolean())
            RsqlTypeConstants.DATE, RsqlTypeConstants.TIME, RsqlTypeConstants.DATETIME -> RawQueryColumn(quoted)
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
