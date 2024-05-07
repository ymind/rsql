package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.operators.*
import team.yi.rsql.core.*

class ZekoIsNullTransformer : ZekoRsqlTransformer(Operator.IS_NULL) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = isNull(selector)

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}

class ZekoIsNotNullTransformer : ZekoRsqlTransformer(Operator.IS_NOT_NULL) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = isNotNull(selector)

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}
