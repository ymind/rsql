package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class ZekoCountEqTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.COUNT_EQ)
class ZekoCountGtTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_GT, Operator.COUNT_GT)
class ZekoCountLtTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_LT, Operator.COUNT_LT)
class ZekoCountGeTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_GE, Operator.COUNT_GE)
class ZekoCountLeTransformer : ZekoAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_LE, Operator.COUNT_LE)
