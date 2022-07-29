package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class JooqMaxEqTransformer : JooqAggregationTransformer(SqlAggregation.MAX, "=")
class JooqMaxGtTransformer : JooqAggregationTransformer(SqlAggregation.MAX, ">")
class JooqMaxLtTransformer : JooqAggregationTransformer(SqlAggregation.MAX, "<")
class JooqMaxGeTransformer : JooqAggregationTransformer(SqlAggregation.MAX, ">=")
class JooqMaxLeTransformer : JooqAggregationTransformer(SqlAggregation.MAX, "<=")
