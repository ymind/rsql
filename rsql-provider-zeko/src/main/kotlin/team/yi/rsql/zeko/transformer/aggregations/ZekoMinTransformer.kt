package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class ZekoMinEqTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.MIN_EQ)
class ZekoMinGtTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_GT, Operator.MIN_GT)
class ZekoMinLtTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_LT, Operator.MIN_LT)
class ZekoMinGeTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_GE, Operator.MIN_GE)
class ZekoMinLeTransformer : ZekoAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_LE, Operator.MIN_LE)
