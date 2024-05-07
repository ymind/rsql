package team.yi.rsql.jooq.transformer.aggregations

import team.yi.rsql.RsqlConstants
import team.yi.rsql.core.*

class JooqCountEqTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_EQUALS, Operator.COUNT_EQ)
class JooqCountGtTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_GT, Operator.COUNT_GT)
class JooqCountLtTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_LT, Operator.COUNT_LT)
class JooqCountGeTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_GE, Operator.COUNT_GE)
class JooqCountLeTransformer : JooqAggregationTransformer(SqlAggregation.COUNT, RsqlConstants.SQL_OPERATOR_LE, Operator.COUNT_LE)
