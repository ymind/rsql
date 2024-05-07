package team.yi.rsql.zeko.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class ZekoAvgEqTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.AVG_EQ)
class ZekoAvgGtTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_GT, Operator.AVG_GT)
class ZekoAvgLtTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_LT, Operator.AVG_LT)
class ZekoAvgGeTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_GE, Operator.AVG_GE)
class ZekoAvgLeTransformer : ZekoAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_LE, Operator.AVG_LE)
