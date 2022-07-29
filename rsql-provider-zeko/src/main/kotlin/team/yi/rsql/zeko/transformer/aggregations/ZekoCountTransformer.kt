package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.core.SqlAggregation

class ZekoCountEqTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, "=")
class ZekoCountGtTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, ">")
class ZekoCountLtTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, "<")
class ZekoCountGeTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, ">=")
class ZekoCountLeTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, "<=")
