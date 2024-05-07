package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.*
import team.yi.rsql.jooq.JooqRsqlUtil

class JooqEqualsTransformer : JooqRsqlTransformer(Operator.EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.eq(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqNotEqualsTransformer : JooqRsqlTransformer(Operator.NOT_EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = JooqRsqlUtil.toValue(arguments[0], typePrompt)
        val queryPart = field.notEqual(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqEqualsIgnoreCaseTransformer : JooqRsqlTransformer(Operator.EQUALS_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.equalIgnoreCase(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqNotEqualsIgnoreCaseTransformer : JooqRsqlTransformer(Operator.NOT_EQUALS_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.notEqualIgnoreCase(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
