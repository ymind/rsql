package team.yi.rsql.zeko

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.RsqlConfig
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
        register(ZekoIsNullTransformer())
        register(ZekoIsNotNullTransformer())

        register(ZekoInTransformer(useRawValue))
        register(ZekoNotInTransformer(useRawValue))

        register(ZekoEqualsTransformer(useRawValue))
        register(ZekoNotEqualsTransformer(useRawValue))
        register(ZekoEqualsIgnoreCaseTransformer(useRawValue))
        register(ZekoNotEqualsIgnoreCaseTransformer(useRawValue))

        register(ZekoIsTrueTransformer())
        register(ZekoIsFalseTransformer())

        register(ZekoIsEmptyTransformer())
        register(ZekoIsNotEmptyTransformer())

        register(ZekoLikeTransformer(useRawValue))
        register(ZekoLikeIgnoreCaseTransformer(useRawValue))
        register(ZekoNotLikeTransformer(useRawValue))
        register(ZekoStartsWithTransformer(useRawValue))
        register(ZekoStartsWithIgnoreCaseTransformer(useRawValue))
        register(ZekoEndsWithTransformer(useRawValue))
        register(ZekoEndsWithIgnoreCaseTransformer(useRawValue))
        register(ZekoContainsTransformer(useRawValue))
        register(ZekoContainsIgnoreCaseTransformer(useRawValue))

        register(ZekoRegexpTransformer())

        register(ZekoGreaterTransformer())
        register(ZekoGreaterEqualsTransformer())
        register(ZekoLessTransformer())
        register(ZekoLessEqualsTransformer())
        register(ZekoBetweenTransformer(useRawValue))
        register(ZekoNotBetweenTransformer(useRawValue))
        register(ZekoBeforeTransformer())
        register(ZekoAfterTransformer())

        // aggregations
        register(ZekoAvgEqTransformer())
        register(ZekoAvgGtTransformer())
        register(ZekoAvgLtTransformer())
        register(ZekoAvgGeTransformer())
        register(ZekoAvgLeTransformer())

        register(ZekoCountEqTransformer())
        register(ZekoCountGtTransformer())
        register(ZekoCountLtTransformer())
        register(ZekoCountGeTransformer())
        register(ZekoCountLeTransformer())

        register(ZekoMaxEqTransformer())
        register(ZekoMaxGtTransformer())
        register(ZekoMaxLtTransformer())
        register(ZekoMaxGeTransformer())
        register(ZekoMaxLeTransformer())

        register(ZekoMinEqTransformer())
        register(ZekoMinGtTransformer())
        register(ZekoMinLtTransformer())
        register(ZekoMinGeTransformer())
        register(ZekoMinLeTransformer())

        register(ZekoSumEqTransformer())
        register(ZekoSumGtTransformer())
        register(ZekoSumLtTransformer())
        register(ZekoSumGeTransformer())
        register(ZekoSumLeTransformer())
    }
}
