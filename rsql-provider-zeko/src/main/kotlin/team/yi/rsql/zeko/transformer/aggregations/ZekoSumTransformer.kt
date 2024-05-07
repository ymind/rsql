package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class ZekoSumEqTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.SUM_EQ)
class ZekoSumGtTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_GT, Operator.SUM_GT)
class ZekoSumLtTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_LT, Operator.SUM_LT)
class ZekoSumGeTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_GE, Operator.SUM_GE)
class ZekoSumLeTransformer : ZekoAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_LE, Operator.SUM_LE)
