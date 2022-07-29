package team.yi.rsql

import team.yi.rsql.core.RsqlQueryPart

interface RsqlTransformer<R> {
    fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<R>?
}
