package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class JooqAvgEqTransformer : JooqAggregationTransformer(SqlAggregation.AVG, "=")
class JooqAvgGtTransformer : JooqAggregationTransformer(SqlAggregation.AVG, ">")
class JooqAvgLtTransformer : JooqAggregationTransformer(SqlAggregation.AVG, "<")
class JooqAvgGeTransformer : JooqAggregationTransformer(SqlAggregation.AVG, ">=")
class JooqAvgLeTransformer : JooqAggregationTransformer(SqlAggregation.AVG, "<=")
