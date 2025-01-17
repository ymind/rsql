package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.*
import team.yi.rsql.mybatisflex.convertType

class MybatisFlexInTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.IN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val values = arguments.map {
            convertType(it, typePrompt)
        }
        val queryBlock = field.`in`(*values.toTypedArray())

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, arguments)
    }
}

class MybatisFlexNotInTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.NOT_IN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val values = arguments.map {
            convertType(it, typePrompt)
        }
        val queryBlock = field.notIn(*values.toTypedArray())

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, arguments)
    }
}
