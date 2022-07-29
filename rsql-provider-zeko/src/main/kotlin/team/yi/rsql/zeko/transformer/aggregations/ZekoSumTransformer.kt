package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class ZekoSumEqTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, "=")
class ZekoSumGtTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, ">")
class ZekoSumLtTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, "<")
class ZekoSumGeTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, ">=")
class ZekoSumLeTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, "<=")
