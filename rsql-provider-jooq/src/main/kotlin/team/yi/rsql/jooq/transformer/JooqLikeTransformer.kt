package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.RsqlQueryPart

class JooqLikeTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.like(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqLikeIgnoreCaseTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.likeIgnoreCase(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqNotLikeTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.notLike(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqStartsWithTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.startsWith(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqStartsWithIgnoreCaseTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.startsWithIgnoreCase(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqEndsWithTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.endsWith(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqEndsWithIgnoreCaseTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.endsWithIgnoreCase(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqContainsTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.contains(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqContainsIgnoreCaseTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.containsIgnoreCase(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
