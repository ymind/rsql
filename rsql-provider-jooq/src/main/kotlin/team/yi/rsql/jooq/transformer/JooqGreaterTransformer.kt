package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.*
import team.yi.rsql.jooq.JooqRsqlUtil

class JooqGreaterTransformer : JooqRsqlTransformer(Operator.GREATER_THAN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqGreaterEqualsTransformer : JooqRsqlTransformer(Operator.GREATER_THAN_OR_EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.ge(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqLessTransformer : JooqRsqlTransformer(Operator.LESS_THAN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqLessEqualsTransformer : JooqRsqlTransformer(Operator.LESS_THAN_OR_EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.le(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqBetweenTransformer : JooqRsqlTransformer(Operator.BETWEEN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val value2 = DSL.value(arguments[1])
        val queryPart = field.between(value1, value2)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1, value2)
    }
}

class JooqNotBetweenTransformer : JooqRsqlTransformer(Operator.NOT_BETWEEN) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val value2 = DSL.value(arguments[1])
        val queryPart = field.notBetween(value1, value2)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1, value2)
    }
}

class JooqBeforeTransformer : JooqRsqlTransformer(Operator.BEFORE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.lt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqAfterTransformer : JooqRsqlTransformer(Operator.AFTER) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.gt(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
