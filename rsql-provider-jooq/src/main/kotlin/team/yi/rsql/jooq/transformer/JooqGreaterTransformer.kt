package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.jooq.JooqRsqlUtil

class JooqGreaterTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqGreaterEqualsTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.ge(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqLessTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqLessEqualsTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.le(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqBetweenTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val value2 = DSL.value(arguments[1])
        val queryPart = field.between(value1, value2)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1, value2)
    }
}

class JooqNotBetweenTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val value2 = DSL.value(arguments[1])
        val queryPart = field.notBetween(value1, value2)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1, value2)
    }
}

class JooqBeforeTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqAfterTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
