package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.*
import team.yi.rsql.mybatisflex.toQueryColumn
import team.yi.rsql.sqlEscape

class MybatisFlexEqualsTransformer : MybatisFlexRsqlTransformer(Operator.EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt)
        val queryPart = field.eq(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class MybatisFlexNotEqualsTransformer : MybatisFlexRsqlTransformer(Operator.NOT_EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt)
        val queryPart = field.ne(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class MybatisFlexEqualsIgnoreCaseTransformer : MybatisFlexRsqlTransformer(Operator.EQUALS_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper(QueryMethods.string(arguments[0].sqlEscape()))
        val queryPart = field.eq(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class MybatisFlexNotEqualsIgnoreCaseTransformer : MybatisFlexRsqlTransformer(Operator.NOT_EQUALS_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.upper(RawQueryColumn(selector))
        val value1 = QueryMethods.upper(QueryMethods.string(arguments[0].sqlEscape()))
        val queryPart = field.ne(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
