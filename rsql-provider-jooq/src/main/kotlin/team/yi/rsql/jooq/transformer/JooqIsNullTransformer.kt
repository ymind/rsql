package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import org.jooq.impl.DSL
import team.yi.rsql.core.*

class JooqIsNullTransformer : JooqRsqlTransformer(Operator.IS_NULL) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val queryPart = field.isNull

        return RsqlQueryPart(selector, arguments, queryPart, true)
    }
}

class JooqIsNotNullTransformer : JooqRsqlTransformer(Operator.IS_NOT_NULL) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryPart> {
        val field = DSL.field(selector)
        val queryPart = field.isNotNull

        return RsqlQueryPart(selector, arguments, queryPart, true)
    }
}
