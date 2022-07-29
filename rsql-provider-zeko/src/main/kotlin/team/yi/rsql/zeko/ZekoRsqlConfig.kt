package team.yi.rsql.zeko

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.RsqlConfig
import team.yi.rsql.core.Operator
import team.yi.rsql.zeko.transformer.*
import team.yi.rsql.zeko.transformer.aggregations.*

class ZekoRsqlConfig(
    /**
     * When this option is true, some string variables in the SQL statement will be replaced by "?",
     * and the relevant parameter values will be saved to `RsqlOutput<Q, R>.queryParts`,
     * you need to process them yourself when executing the SQL statement
     */
    useRawValue: Boolean = true,
) : RsqlConfig<QueryBlock>() {
    init {
        register(ZekoIsNullTransformer(), Operator.IS_NULL)
        register(ZekoIsNotNullTransformer(), Operator.IS_NOT_NULL)

        register(ZekoInTransformer(useRawValue), Operator.IN)
        register(ZekoNotInTransformer(useRawValue), Operator.NOT_IN)

        register(ZekoEqualsTransformer(useRawValue), Operator.EQUALS)
        register(ZekoNotEqualsTransformer(useRawValue), Operator.NOT_EQUALS)
        register(ZekoEqualsIgnoreCaseTransformer(useRawValue), Operator.EQUALS_IGNORE_CASE)
        register(ZekoNotEqualsIgnoreCaseTransformer(useRawValue), Operator.NOT_EQUALS_IGNORE_CASE)

        register(ZekoIsTrueTransformer(), Operator.IS_TRUE)
        register(ZekoIsFalseTransformer(), Operator.IS_FALSE)

        register(ZekoIsEmptyTransformer(), Operator.IS_EMPTY)
        register(ZekoIsNotEmptyTransformer(), Operator.IS_NOT_EMPTY)

        register(ZekoLikeTransformer(useRawValue), Operator.LIKE)
        register(ZekoLikeIgnoreCaseTransformer(useRawValue), Operator.LIKE_IGNORE_CASE)
        register(ZekoNotLikeTransformer(useRawValue), Operator.NOT_LIKE)
        register(ZekoStartsWithTransformer(useRawValue), Operator.STARTS_WITH)
        register(ZekoStartsWithIgnoreCaseTransformer(useRawValue), Operator.STARTS_WITH_IGNORE_CASE)
        register(ZekoEndsWithTransformer(useRawValue), Operator.ENDS_WITH)
        register(ZekoEndsWithIgnoreCaseTransformer(useRawValue), Operator.ENDS_WITH_IGNORE_CASE)
        register(ZekoContainsTransformer(useRawValue), Operator.CONTAINS)
        register(ZekoContainsIgnoreCaseTransformer(useRawValue), Operator.CONTAINS_IGNORE_CASE)

        register(ZekoRegexpTransformer(), Operator.REGEX)

        register(ZekoGreaterTransformer(), Operator.GREATER_THAN)
        register(ZekoGreaterEqualsTransformer(), Operator.GREATER_THAN_OR_EQUALS)
        register(ZekoLessTransformer(), Operator.LESS_THAN)
        register(ZekoLessEqualsTransformer(), Operator.LESS_THAN_OR_EQUALS)
        register(ZekoBetweenTransformer(useRawValue), Operator.BETWEEN)
        register(ZekoNotBetweenTransformer(useRawValue), Operator.NOT_BETWEEN)
        register(ZekoBeforeTransformer(), Operator.BEFORE)
        register(ZekoAfterTransformer(), Operator.AFTER)

        // aggregations
        register(ZekoAvgEqTransformer(), Operator.AVG_EQ)
        register(ZekoAvgGtTransformer(), Operator.AVG_GT)
        register(ZekoAvgLtTransformer(), Operator.AVG_LT)
        register(ZekoAvgGeTransformer(), Operator.AVG_GE)
        register(ZekoAvgLeTransformer(), Operator.AVG_LE)

        register(ZekoCountEqTransformer(), Operator.COUNT_EQ)
        register(ZekoCountGtTransformer(), Operator.COUNT_GT)
        register(ZekoCountLtTransformer(), Operator.COUNT_LT)
        register(ZekoCountGeTransformer(), Operator.COUNT_GE)
        register(ZekoCountLeTransformer(), Operator.COUNT_LE)

        register(ZekoMaxEqTransformer(), Operator.MAX_EQ)
        register(ZekoMaxGtTransformer(), Operator.MAX_GT)
        register(ZekoMaxLtTransformer(), Operator.MAX_LT)
        register(ZekoMaxGeTransformer(), Operator.MAX_GE)
        register(ZekoMaxLeTransformer(), Operator.MAX_LE)

        register(ZekoMinEqTransformer(), Operator.MIN_EQ)
        register(ZekoMinGtTransformer(), Operator.MIN_GT)
        register(ZekoMinLtTransformer(), Operator.MIN_LT)
        register(ZekoMinGeTransformer(), Operator.MIN_GE)
        register(ZekoMinLeTransformer(), Operator.MIN_LE)

        register(ZekoSumEqTransformer(), Operator.SUM_EQ)
        register(ZekoSumGtTransformer(), Operator.SUM_GT)
        register(ZekoSumLtTransformer(), Operator.SUM_LT)
        register(ZekoSumGeTransformer(), Operator.SUM_GE)
        register(ZekoSumLeTransformer(), Operator.SUM_LE)
    }
}
