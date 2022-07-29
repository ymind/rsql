package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.operators.eq
import team.yi.rsql.core.RsqlQueryPart

class ZekoIsTrueTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = 1
        val queryBlock = eq(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class ZekoIsFalseTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = 0
        val queryBlock = eq(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
