package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.operators.regexp
import team.yi.rsql.core.RsqlQueryPart

class ZekoRegexpTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = regexp(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
