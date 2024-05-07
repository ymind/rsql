package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.operators.eq
import team.yi.rsql.core.*

class ZekoIsTrueTransformer : ZekoRsqlTransformer(Operator.IS_TRUE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = 1
        val queryBlock = eq(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class ZekoIsFalseTransformer : ZekoRsqlTransformer(Operator.IS_FALSE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = 0
        val queryBlock = eq(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
