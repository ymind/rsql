package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.*
import team.yi.rsql.core.*

class MybatisFlexIsTrueTransformer : MybatisFlexRsqlTransformer(Operator.IS_TRUE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = QueryMethods.true_()
        val queryBlock = field.eq(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class MybatisFlexIsFalseTransformer : MybatisFlexRsqlTransformer(Operator.IS_FALSE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryCondition> {
        val field = RawQueryColumn(selector)
        val value1 = QueryMethods.false_()
        val queryBlock = field.eq(value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
