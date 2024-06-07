package team.yi.rsql

import cz.jirutka.rsql.parser.ast.ComparisonNode
import team.yi.rsql.core.*

interface RsqlTransformer<R> {
    val operator: Operator

    fun supports(rsqlNode: ComparisonNode, rsqlOperator: RsqlOperator): Boolean = true

    fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<R>?
}
