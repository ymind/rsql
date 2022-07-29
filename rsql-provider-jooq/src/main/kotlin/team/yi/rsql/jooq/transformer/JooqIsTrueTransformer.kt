package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.RsqlQueryPart

class JooqIsTrueTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(true)
        val queryPart = field.isTrue

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}

class JooqIsFalseTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(false)
        val queryPart = field.isFalse

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
