package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.*

class MybatisFlexIsNullTransformer : MybatisFlexRsqlTransformer(Operator.IS_NULL) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val queryBlock = field.isNull

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}

class MybatisFlexIsNotNullTransformer : MybatisFlexRsqlTransformer(Operator.IS_NOT_NULL) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val queryBlock = field.isNotNull

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}
