package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class ZekoMaxEqTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.MAX_EQ)
class ZekoMaxGtTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_GT, Operator.MAX_GT)
class ZekoMaxLtTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_LT, Operator.MAX_LT)
class ZekoMaxGeTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_GE, Operator.MAX_GE)
class ZekoMaxLeTransformer : ZekoAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_LE, Operator.MAX_LE)
