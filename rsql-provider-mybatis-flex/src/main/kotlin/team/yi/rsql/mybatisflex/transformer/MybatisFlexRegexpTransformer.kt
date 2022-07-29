package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.mybatisflex.*
import team.yi.rsql.sqlEscape

class MybatisFlexRegexpTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = MybatisFlexRsqlUtil.toValue(arguments[0].sqlEscape(), typePrompt)
        val queryBlock = field.likeRegex(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
