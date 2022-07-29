package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.jooq.JooqRsqlUtil

class JooqInTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val values = arguments.map { JooqRsqlUtil.toValue(it, typePrompt) }
        val queryPart = field.`in`(values)

        return RsqlQueryPart(selector, arguments, queryPart, true, values)
    }
}

class JooqNotInTransformer : JooqRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val values = arguments.map { JooqRsqlUtil.toValue(it, typePrompt) }
        val queryPart = field.notIn(values)

        return RsqlQueryPart(selector, arguments, queryPart, true, values)
    }
}
