package team.yi.rsql

import team.yi.rsql.core.*

interface RsqlTransformer<R> {
    val operator: Operator

    fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<R>?
}
