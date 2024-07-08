package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.*
import team.yi.rsql.sqlEscape

class MybatisFlexLikeTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.LIKE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = arguments[0].sqlEscape()
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexLikeIgnoreCaseTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.LIKE_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper(QueryMethods.string(arguments[0].sqlEscape()))
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexNotLikeTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.NOT_LIKE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = arguments[0].sqlEscape()
        val queryBlock = field.notLike(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexStartsWithTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.STARTS_WITH) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = "${arguments[0].sqlEscape()}%"
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexStartsWithIgnoreCaseTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.STARTS_WITH_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper("${QueryMethods.string(arguments[0].sqlEscape())}%")
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexEndsWithTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.ENDS_WITH) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = "%${arguments[0].sqlEscape()}"
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexEndsWithIgnoreCaseTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.ENDS_WITH_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper(QueryMethods.string("%${arguments[0].sqlEscape()}"))
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexContainsTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.CONTAINS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = "%${arguments[0].sqlEscape()}%"
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class MybatisFlexContainsIgnoreCaseTransformer(private val useRawValue: Boolean) : MybatisFlexRsqlTransformer(Operator.CONTAINS_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper(QueryMethods.string("%${arguments[0].sqlEscape()}%"))
        val queryBlock = field.likeRaw(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}
