package team.yi.rsql.jooq

import org.jooq.*
import team.yi.rsql.RsqlConfig
import team.yi.rsql.core.Operator
import team.yi.rsql.jooq.transformer.*
import team.yi.rsql.jooq.transformer.aggregations.*

class JooqRsqlConfig : RsqlConfig<QueryPart>() {
    init {
        register(JooqIsNullTransformer(), Operator.IS_NULL)
        register(JooqIsNotNullTransformer(), Operator.IS_NOT_NULL)

        register(JooqInTransformer(), Operator.IN)
        register(JooqNotInTransformer(), Operator.NOT_IN)

        register(JooqEqualsTransformer(), Operator.EQUALS)
        register(JooqNotEqualsTransformer(), Operator.NOT_EQUALS)
        register(JooqEqualsIgnoreCaseTransformer(), Operator.EQUALS_IGNORE_CASE)
        register(JooqNotEqualsIgnoreCaseTransformer(), Operator.NOT_EQUALS_IGNORE_CASE)

        register(JooqIsTrueTransformer(), Operator.IS_TRUE)
        register(JooqIsFalseTransformer(), Operator.IS_FALSE)

        register(JooqIsEmptyTransformer(), Operator.IS_EMPTY)
        register(JooqIsNotEmptyTransformer(), Operator.IS_NOT_EMPTY)

        register(JooqLikeTransformer(), Operator.LIKE)
        register(JooqLikeIgnoreCaseTransformer(), Operator.LIKE_IGNORE_CASE)
        register(JooqNotLikeTransformer(), Operator.NOT_LIKE)
        register(JooqStartsWithTransformer(), Operator.STARTS_WITH)
        register(JooqStartsWithIgnoreCaseTransformer(), Operator.STARTS_WITH_IGNORE_CASE)
        register(JooqEndsWithTransformer(), Operator.ENDS_WITH)
        register(JooqEndsWithIgnoreCaseTransformer(), Operator.ENDS_WITH_IGNORE_CASE)
        register(JooqContainsTransformer(), Operator.CONTAINS)
        register(JooqContainsIgnoreCaseTransformer(), Operator.CONTAINS_IGNORE_CASE)

        register(JooqRegexpTransformer(), Operator.REGEX)

        register(JooqGreaterTransformer(), Operator.GREATER_THAN)
        register(JooqGreaterEqualsTransformer(), Operator.GREATER_THAN_OR_EQUALS)
        register(JooqLessTransformer(), Operator.LESS_THAN)
        register(JooqLessEqualsTransformer(), Operator.LESS_THAN_OR_EQUALS)
        register(JooqBetweenTransformer(), Operator.BETWEEN)
        register(JooqNotBetweenTransformer(), Operator.NOT_BETWEEN)
        register(JooqBeforeTransformer(), Operator.BEFORE)
        register(JooqAfterTransformer(), Operator.AFTER)

        // aggregations
        register(JooqAvgEqTransformer(), Operator.AVG_EQ)
        register(JooqAvgGtTransformer(), Operator.AVG_GT)
        register(JooqAvgLtTransformer(), Operator.AVG_LT)
        register(JooqAvgGeTransformer(), Operator.AVG_GE)
        register(JooqAvgLeTransformer(), Operator.AVG_LE)

        register(JooqCountEqTransformer(), Operator.COUNT_EQ)
        register(JooqCountGtTransformer(), Operator.COUNT_GT)
        register(JooqCountLtTransformer(), Operator.COUNT_LT)
        register(JooqCountGeTransformer(), Operator.COUNT_GE)
        register(JooqCountLeTransformer(), Operator.COUNT_LE)

        register(JooqMaxEqTransformer(), Operator.MAX_EQ)
        register(JooqMaxGtTransformer(), Operator.MAX_GT)
        register(JooqMaxLtTransformer(), Operator.MAX_LT)
        register(JooqMaxGeTransformer(), Operator.MAX_GE)
        register(JooqMaxLeTransformer(), Operator.MAX_LE)

        register(JooqMinEqTransformer(), Operator.MIN_EQ)
        register(JooqMinGtTransformer(), Operator.MIN_GT)
        register(JooqMinLtTransformer(), Operator.MIN_LT)
        register(JooqMinGeTransformer(), Operator.MIN_GE)
        register(JooqMinLeTransformer(), Operator.MIN_LE)

        register(JooqSumEqTransformer(), Operator.SUM_EQ)
        register(JooqSumGtTransformer(), Operator.SUM_GT)
        register(JooqSumLtTransformer(), Operator.SUM_LT)
        register(JooqSumGeTransformer(), Operator.SUM_GE)
        register(JooqSumLeTransformer(), Operator.SUM_LE)
    }
}
