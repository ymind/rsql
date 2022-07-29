package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class ZekoAvgEqTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, "=")
class ZekoAvgGtTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, ">")
class ZekoAvgLtTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, "<")
class ZekoAvgGeTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, ">=")
class ZekoAvgLeTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, "<=")
