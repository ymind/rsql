package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.sqlEscape

class MybatisFlexEqualsTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = arguments[0].sqlEscape()
        val queryPart = field.eq(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class MybatisFlexNotEqualsTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = arguments[0].sqlEscape()
        val queryPart = field.ne(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class MybatisFlexEqualsIgnoreCaseTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper(arguments[0].sqlEscape())
        val queryPart = field.eq(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class MybatisFlexNotEqualsIgnoreCaseTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper(arguments[0].sqlEscape())
        val queryPart = field.ne(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
