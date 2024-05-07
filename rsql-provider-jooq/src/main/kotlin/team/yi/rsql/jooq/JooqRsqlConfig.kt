package team.yi.rsql.jooq

import org.jooq.QueryPart
import team.yi.rsql.RsqlConfig
import team.yi.rsql.jooq.transformer.*
import team.yi.rsql.jooq.transformer.aggregations.*

class JooqRsqlConfig : RsqlConfig<QueryPart>() {
    init {
        register(JooqIsNullTransformer())
        register(JooqIsNotNullTransformer())

        register(JooqInTransformer())
        register(JooqNotInTransformer())

        register(JooqEqualsTransformer())
        register(JooqNotEqualsTransformer())
        register(JooqEqualsIgnoreCaseTransformer())
        register(JooqNotEqualsIgnoreCaseTransformer())

        register(JooqIsTrueTransformer())
        register(JooqIsFalseTransformer())

        register(JooqIsEmptyTransformer())
        register(JooqIsNotEmptyTransformer())

        register(JooqLikeTransformer())
        register(JooqLikeIgnoreCaseTransformer())
        register(JooqNotLikeTransformer())
        register(JooqStartsWithTransformer())
        register(JooqStartsWithIgnoreCaseTransformer())
        register(JooqEndsWithTransformer())
        register(JooqEndsWithIgnoreCaseTransformer())
        register(JooqContainsTransformer())
        register(JooqContainsIgnoreCaseTransformer())

        register(JooqRegexpTransformer())

        register(JooqGreaterTransformer())
        register(JooqGreaterEqualsTransformer())
        register(JooqLessTransformer())
        register(JooqLessEqualsTransformer())
        register(JooqBetweenTransformer())
        register(JooqNotBetweenTransformer())
        register(JooqBeforeTransformer())
        register(JooqAfterTransformer())

        // aggregations
        register(JooqAvgEqTransformer())
        register(JooqAvgGtTransformer())
        register(JooqAvgLtTransformer())
        register(JooqAvgGeTransformer())
        register(JooqAvgLeTransformer())

        register(JooqCountEqTransformer())
        register(JooqCountGtTransformer())
        register(JooqCountLtTransformer())
        register(JooqCountGeTransformer())
        register(JooqCountLeTransformer())

        register(JooqMaxEqTransformer())
        register(JooqMaxGtTransformer())
        register(JooqMaxLtTransformer())
        register(JooqMaxGeTransformer())
        register(JooqMaxLeTransformer())

        register(JooqMinEqTransformer())
        register(JooqMinGtTransformer())
        register(JooqMinLtTransformer())
        register(JooqMinGeTransformer())
        register(JooqMinLeTransformer())

        register(JooqSumEqTransformer())
        register(JooqSumGtTransformer())
        register(JooqSumLtTransformer())
        register(JooqSumGeTransformer())
        register(JooqSumLeTransformer())
    }
}
