package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class JooqCountEqTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, "=")
class JooqCountGtTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, ">")
class JooqCountLtTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, "<")
class JooqCountGeTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, ">=")
class JooqCountLeTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, "<=")
