package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class JooqSumEqTransformer : JooqAggregationTransformer(SqlAggregation.SUM, "=")
class JooqSumGtTransformer : JooqAggregationTransformer(SqlAggregation.SUM, ">")
class JooqSumLtTransformer : JooqAggregationTransformer(SqlAggregation.SUM, "<")
class JooqSumGeTransformer : JooqAggregationTransformer(SqlAggregation.SUM, ">=")
class JooqSumLeTransformer : JooqAggregationTransformer(SqlAggregation.SUM, "<=")
