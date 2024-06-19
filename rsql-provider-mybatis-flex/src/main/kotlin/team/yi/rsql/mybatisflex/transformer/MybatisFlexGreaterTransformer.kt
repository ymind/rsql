package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.*
import team.yi.rsql.mybatisflex.*

class MybatisFlexGreaterTransformer : MybatisFlexRsqlTransformer(Operator.GREATER_THAN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt)
        val queryBlock = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexGreaterEqualsTransformer : MybatisFlexRsqlTransformer(Operator.GREATER_THAN_OR_EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt)
        val queryBlock = field.ge(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexLessTransformer : MybatisFlexRsqlTransformer(Operator.LESS_THAN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt)
        val queryBlock = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexLessEqualsTransformer : MybatisFlexRsqlTransformer(Operator.LESS_THAN_OR_EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt)
        val queryBlock = field.le(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexBetweenTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.BETWEEN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = convertType(arguments[0], typePrompt)
        val value2 = convertType(arguments[1], typePrompt)
        val queryBlock = field.between(value1, value2)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1, value2)
    }
}

class MybatisFlexNotBetweenTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.NOT_BETWEEN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = convertType(arguments[0], typePrompt)
        val value2 = convertType(arguments[1], typePrompt)
        val queryBlock = field.notBetween(value1, value2)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1, value2)
    }
}

class MybatisFlexBeforeTransformer : MybatisFlexRsqlTransformer(Operator.BEFORE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt, true)
        val queryBlock = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexAfterTransformer : MybatisFlexRsqlTransformer(Operator.AFTER) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt, true)
        val queryBlock = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
