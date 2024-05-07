package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.core.*
import team.yi.rsql.zeko.*

class ZekoIsEmptyTransformer : ZekoRsqlTransformer(Operator.IS_EMPTY) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = isEmpty(selector)

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}

class ZekoIsNotEmptyTransformer : ZekoRsqlTransformer(Operator.IS_NOT_EMPTY) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = isNotEmpty(selector)

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}
