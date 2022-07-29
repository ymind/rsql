package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.zeko.*

class ZekoIsEmptyTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = isEmpty(selector)

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}

class ZekoIsNotEmptyTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = isNotEmpty(selector)

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}
