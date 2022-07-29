package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.RsqlQueryPart

class JooqIsEmptyTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val queryPart = field.isNull.or(DSL.length(selector).eq(0))

        return RsqlQueryPart(selector, arguments, queryPart, true)
    }
}

class JooqIsNotEmptyTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val queryPart = field.isNotNull.and(DSL.length(selector).gt(0))

        return RsqlQueryPart(selector, arguments, queryPart, true)
    }
}
