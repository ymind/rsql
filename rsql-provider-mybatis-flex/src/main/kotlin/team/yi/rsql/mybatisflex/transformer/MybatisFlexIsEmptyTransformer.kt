package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.RsqlQueryPart

class MybatisFlexIsEmptyTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.length(RawQueryColumn(selector))
        val queryBlock = field.isNull.or(field.eq(0))

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}

class MybatisFlexIsNotEmptyTransformer : MybatisFlexRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = QueryMethods.length(RawQueryColumn(selector))
        val queryBlock = field.isNotNull.and(field.gt(0))

        return RsqlQueryPart(selector, arguments, queryBlock, true)
    }
}
