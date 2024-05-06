package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.mybatisflex.MybatisFlexRsqlUtil

class MybatisFlexInTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val values = arguments.map {
            if (typePrompt == "b" || typePrompt == "bool") {
                it.toBoolean()
            } else {
                MybatisFlexRsqlUtil.toValue(it, typePrompt)
            }
        }
        val queryBlock = field.`in`(*values.toTypedArray())

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, arguments)
    }
}

class MybatisFlexNotInTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val values = arguments.map {
            if (typePrompt == "b" || typePrompt == "bool") {
                it.toBoolean()
            } else {
                MybatisFlexRsqlUtil.toValue(it, typePrompt)
            }
        }
        val queryBlock = field.notIn(*values.toTypedArray())

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, arguments)
    }
}
