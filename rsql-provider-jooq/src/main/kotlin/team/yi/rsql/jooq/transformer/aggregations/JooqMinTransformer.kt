package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class JooqMinEqTransformer : JooqAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.MIN_EQ)
class JooqMinGtTransformer : JooqAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_GT, Operator.MIN_GT)
class JooqMinLtTransformer : JooqAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_LT, Operator.MIN_LT)
class JooqMinGeTransformer : JooqAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_GE, Operator.MIN_GE)
class JooqMinLeTransformer : JooqAggregationTransformer(SqlAggregation.MIN, RsqlConstants.SQL_OPERATOR_LE, Operator.MIN_LE)
