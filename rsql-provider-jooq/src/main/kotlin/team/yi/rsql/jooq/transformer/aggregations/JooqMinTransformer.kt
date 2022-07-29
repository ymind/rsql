package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class JooqMinEqTransformer : JooqAggregationTransformer(SqlAggregation.MIN, "=")
class JooqMinGtTransformer : JooqAggregationTransformer(SqlAggregation.MIN, ">")
class JooqMinLtTransformer : JooqAggregationTransformer(SqlAggregation.MIN, "<")
class JooqMinGeTransformer : JooqAggregationTransformer(SqlAggregation.MIN, ">=")
class JooqMinLeTransformer : JooqAggregationTransformer(SqlAggregation.MIN, "<=")
