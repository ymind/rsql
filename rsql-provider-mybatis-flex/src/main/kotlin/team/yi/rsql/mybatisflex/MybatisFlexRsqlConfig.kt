package team.yi.rsql.mybatisflex

import com.mybatisflex.core.query.QueryCondition
import team.yi.rsql.RsqlConfig
import team.yi.rsql.mybatisflex.transformer.*
import team.yi.rsql.mybatisflex.transformer.aggregations.*

class MybatisFlexRsqlConfig(
    val useRawValue: Boolean = true,
) : RsqlConfig<QueryCondition>() {
    init {
        register(MybatisFlexIsNullTransformer())
        register(MybatisFlexIsNotNullTransformer())

        register(MybatisFlexInTransformer(useRawValue))
        register(MybatisFlexNotInTransformer(useRawValue))

        register(MybatisFlexEqualsTransformer())
        register(MybatisFlexNotEqualsTransformer())
        register(MybatisFlexEqualsIgnoreCaseTransformer())
        register(MybatisFlexNotEqualsIgnoreCaseTransformer())

        register(MybatisFlexIsTrueTransformer())
        register(MybatisFlexIsFalseTransformer())

        register(MybatisFlexIsEmptyTransformer())
        register(MybatisFlexIsNotEmptyTransformer())

        register(MybatisFlexLikeTransformer(useRawValue))
        register(MybatisFlexLikeIgnoreCaseTransformer(useRawValue))
        register(MybatisFlexNotLikeTransformer(useRawValue))
        register(MybatisFlexStartsWithTransformer(useRawValue))
        register(MybatisFlexStartsWithIgnoreCaseTransformer(useRawValue))
        register(MybatisFlexEndsWithTransformer(useRawValue))
        register(MybatisFlexEndsWithIgnoreCaseTransformer(useRawValue))
        register(MybatisFlexContainsTransformer(useRawValue))
        register(MybatisFlexContainsIgnoreCaseTransformer(useRawValue))

        register(MybatisFlexRegexpTransformer())

        register(MybatisFlexGreaterTransformer())
        register(MybatisFlexGreaterEqualsTransformer())
        register(MybatisFlexLessTransformer())
        register(MybatisFlexLessEqualsTransformer())
        register(MybatisFlexBetweenTransformer(useRawValue))
        register(MybatisFlexNotBetweenTransformer(useRawValue))
        register(MybatisFlexBeforeTransformer())
        register(MybatisFlexAfterTransformer())

        // aggregations
        register(MybatisFlexAvgEqTransformer())
        register(MybatisFlexAvgGtTransformer())
        register(MybatisFlexAvgLtTransformer())
        register(MybatisFlexAvgGeTransformer())
        register(MybatisFlexAvgLeTransformer())

        register(MybatisFlexCountEqTransformer())
        register(MybatisFlexCountGtTransformer())
        register(MybatisFlexCountLtTransformer())
        register(MybatisFlexCountGeTransformer())
        register(MybatisFlexCountLeTransformer())

        register(MybatisFlexMaxEqTransformer())
        register(MybatisFlexMaxGtTransformer())
        register(MybatisFlexMaxLtTransformer())
        register(MybatisFlexMaxGeTransformer())
        register(MybatisFlexMaxLeTransformer())

        register(MybatisFlexMinEqTransformer())
        register(MybatisFlexMinGtTransformer())
        register(MybatisFlexMinLtTransformer())
        register(MybatisFlexMinGeTransformer())
        register(MybatisFlexMinLeTransformer())

        register(MybatisFlexSumEqTransformer())
        register(MybatisFlexSumGtTransformer())
        register(MybatisFlexSumLtTransformer())
        register(MybatisFlexSumGeTransformer())
        register(MybatisFlexSumLeTransformer())
    }
}
