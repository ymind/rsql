package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.mybatisflex.MybatisFlexRsqlUtil

class MybatisFlexGreaterTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], typePrompt)
        val queryBlock = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexGreaterEqualsTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], typePrompt)
        val queryBlock = field.ge(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexLessTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], typePrompt)
        val queryBlock = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexLessEqualsTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], typePrompt)
        val queryBlock = field.le(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexBetweenTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], typePrompt)
        val value2 = MybatisFlexRsqlUtil.toValue(arguments[1], typePrompt)
        val queryBlock = field.between(value1, value2)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1, value2)
    }
}

class MybatisFlexNotBetweenTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], typePrompt)
        val value2 = MybatisFlexRsqlUtil.toValue(arguments[1], typePrompt)
        val queryBlock = field.notBetween(value1, value2)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1, value2)
    }
}

class MybatisFlexBeforeTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], "dt")
        val queryBlock = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexAfterTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0], "dt")
        val queryBlock = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
