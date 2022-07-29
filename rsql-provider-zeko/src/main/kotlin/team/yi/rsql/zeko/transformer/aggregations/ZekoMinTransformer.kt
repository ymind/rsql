package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class ZekoMinEqTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, "=")
class ZekoMinGtTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, ">")
class ZekoMinLtTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, "<")
class ZekoMinGeTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, ">=")
class ZekoMinLeTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, "<=")
