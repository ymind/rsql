package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.operators.*
import team.yi.rsql.core.RsqlQueryPart

class ZekoInTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = inList(selector, arguments, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, arguments)
    }
}

class ZekoNotInTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val queryBlock = notInList(selector, arguments, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, arguments)
    }
}
