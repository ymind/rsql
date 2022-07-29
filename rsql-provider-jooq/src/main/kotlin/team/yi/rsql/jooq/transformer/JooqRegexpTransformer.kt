package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.RsqlQueryPart

class JooqRegexpTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val value1 = DSL.value(arguments[0])
        val queryPart = field.likeRegex(value1)

        return RsqlQueryPart(selector, arguments, queryPart, true, value1)
    }
}
