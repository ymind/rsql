package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.QueryCondition
import team.yi.rsql.RsqlConfig
import team.yi.rsql.core.Operator
import team.yi.rsql.mybatisflex.transformer.*
import team.yi.rsql.mybatisflex.transformer.aggregations.*

class MybatisFlexRsqlConfig(
    val useRawValue: Boolean = true,
) : RsqlConfig<QueryCondition>() {
    init {
        register(MybatisFlexIsNullTransformer(), Operator.IS_NULL)
        register(MybatisFlexIsNotNullTransformer(), Operator.IS_NOT_NULL)

        register(MybatisFlexInTransformer(useRawValue), Operator.IN)
        register(MybatisFlexNotInTransformer(useRawValue), Operator.NOT_IN)

        register(MybatisFlexEqualsTransformer(), Operator.EQUALS)
        register(MybatisFlexNotEqualsTransformer(), Operator.NOT_EQUALS)
        register(MybatisFlexEqualsIgnoreCaseTransformer(), Operator.EQUALS_IGNORE_CASE)
        register(MybatisFlexNotEqualsIgnoreCaseTransformer(), Operator.NOT_EQUALS_IGNORE_CASE)

        register(MybatisFlexIsTrueTransformer(), Operator.IS_TRUE)
        register(MybatisFlexIsFalseTransformer(), Operator.IS_FALSE)

        register(MybatisFlexIsEmptyTransformer(), Operator.IS_EMPTY)
        register(MybatisFlexIsNotEmptyTransformer(), Operator.IS_NOT_EMPTY)

        register(MybatisFlexLikeTransformer(useRawValue), Operator.LIKE)
        register(MybatisFlexLikeIgnoreCaseTransformer(useRawValue), Operator.LIKE_IGNORE_CASE)
        register(MybatisFlexNotLikeTransformer(useRawValue), Operator.NOT_LIKE)
        register(MybatisFlexStartsWithTransformer(useRawValue), Operator.STARTS_WITH)
        register(MybatisFlexStartsWithIgnoreCaseTransformer(useRawValue), Operator.STARTS_WITH_IGNORE_CASE)
        register(MybatisFlexEndsWithTransformer(useRawValue), Operator.ENDS_WITH)
        register(MybatisFlexEndsWithIgnoreCaseTransformer(useRawValue), Operator.ENDS_WITH_IGNORE_CASE)
        register(MybatisFlexContainsTransformer(useRawValue), Operator.CONTAINS)
        register(MybatisFlexContainsIgnoreCaseTransformer(useRawValue), Operator.CONTAINS_IGNORE_CASE)

        register(MybatisFlexRegexpTransformer(), Operator.REGEX)

        register(MybatisFlexGreaterTransformer(), Operator.GREATER_THAN)
        register(MybatisFlexGreaterEqualsTransformer(), Operator.GREATER_THAN_OR_EQUALS)
        register(MybatisFlexLessTransformer(), Operator.LESS_THAN)
        register(MybatisFlexLessEqualsTransformer(), Operator.LESS_THAN_OR_EQUALS)
        register(MybatisFlexBetweenTransformer(useRawValue), Operator.BETWEEN)
        register(MybatisFlexNotBetweenTransformer(useRawValue), Operator.NOT_BETWEEN)
        register(MybatisFlexBeforeTransformer(), Operator.BEFORE)
        register(MybatisFlexAfterTransformer(), Operator.AFTER)

        // aggregations
        register(MybatisFlexAvgEqTransformer(), Operator.AVG_EQ)
        register(MybatisFlexAvgGtTransformer(), Operator.AVG_GT)
        register(MybatisFlexAvgLtTransformer(), Operator.AVG_LT)
        register(MybatisFlexAvgGeTransformer(), Operator.AVG_GE)
        register(MybatisFlexAvgLeTransformer(), Operator.AVG_LE)

        register(MybatisFlexCountEqTransformer(), Operator.COUNT_EQ)
        register(MybatisFlexCountGtTransformer(), Operator.COUNT_GT)
        register(MybatisFlexCountLtTransformer(), Operator.COUNT_LT)
        register(MybatisFlexCountGeTransformer(), Operator.COUNT_GE)
        register(MybatisFlexCountLeTransformer(), Operator.COUNT_LE)

        register(MybatisFlexMaxEqTransformer(), Operator.MAX_EQ)
        register(MybatisFlexMaxGtTransformer(), Operator.MAX_GT)
        register(MybatisFlexMaxLtTransformer(), Operator.MAX_LT)
        register(MybatisFlexMaxGeTransformer(), Operator.MAX_GE)
        register(MybatisFlexMaxLeTransformer(), Operator.MAX_LE)

        register(MybatisFlexMinEqTransformer(), Operator.MIN_EQ)
        register(MybatisFlexMinGtTransformer(), Operator.MIN_GT)
        register(MybatisFlexMinLtTransformer(), Operator.MIN_LT)
        register(MybatisFlexMinGeTransformer(), Operator.MIN_GE)
        register(MybatisFlexMinLeTransformer(), Operator.MIN_LE)

        register(MybatisFlexSumEqTransformer(), Operator.SUM_EQ)
        register(MybatisFlexSumGtTransformer(), Operator.SUM_GT)
        register(MybatisFlexSumLtTransformer(), Operator.SUM_LT)
        register(MybatisFlexSumGeTransformer(), Operator.SUM_GE)
        register(MybatisFlexSumLeTransformer(), Operator.SUM_LE)
    }
}
