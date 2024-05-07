package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class JooqSumEqTransformer : JooqAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.SUM_EQ)
class JooqSumGtTransformer : JooqAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_GT, Operator.SUM_GT)
class JooqSumLtTransformer : JooqAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_LT, Operator.SUM_LT)
class JooqSumGeTransformer : JooqAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_GE, Operator.SUM_GE)
class JooqSumLeTransformer : JooqAggregationTransformer(SqlAggregation.SUM, RsqlConstants.SQL_OPERATOR_LE, Operator.SUM_LE)
