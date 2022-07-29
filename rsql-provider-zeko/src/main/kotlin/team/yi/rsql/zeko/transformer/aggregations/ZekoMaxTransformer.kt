package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class ZekoMaxEqTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, "=")
class ZekoMaxGtTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, ">")
class ZekoMaxLtTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, "<")
class ZekoMaxGeTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, ">=")
class ZekoMaxLeTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, "<=")
