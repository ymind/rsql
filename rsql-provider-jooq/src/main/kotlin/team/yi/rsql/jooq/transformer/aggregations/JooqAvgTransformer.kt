package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class JooqAvgEqTransformer : JooqAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.AVG_EQ)
class JooqAvgGtTransformer : JooqAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_GT, Operator.AVG_GT)
class JooqAvgLtTransformer : JooqAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_LT, Operator.AVG_LT)
class JooqAvgGeTransformer : JooqAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_GE, Operator.AVG_GE)
class JooqAvgLeTransformer : JooqAggregationTransformer(SqlAggregation.AVG, RsqlConstants.SQL_OPERATOR_LE, Operator.AVG_LE)
