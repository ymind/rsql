package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.*
import team.yi.rsql.mybatisflex.*

class MybatisFlexRegexpTransformer : MybatisFlexRsqlTransformer(Operator.REGEX) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = toQueryColumn(arguments[0], typePrompt)
        val queryBlock = field.likeRegex(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
