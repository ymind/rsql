package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class JooqMaxEqTransformer : JooqAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.MAX_EQ)
class JooqMaxGtTransformer : JooqAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_GT, Operator.MAX_GT)
class JooqMaxLtTransformer : JooqAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_LT, Operator.MAX_LT)
class JooqMaxGeTransformer : JooqAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_GE, Operator.MAX_GE)
class JooqMaxLeTransformer : JooqAggregationTransformer(SqlAggregation.MAX, RsqlConstants.SQL_OPERATOR_LE, Operator.MAX_LE)
