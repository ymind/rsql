package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.*

class MybatisFlexMinEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.EQUALS, Operator.MIN_EQ)
class MybatisFlexMinGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.GT, Operator.MIN_GT)
class MybatisFlexMinLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.LT, Operator.MIN_LT)
class MybatisFlexMinGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.GE, Operator.MIN_GE)
class MybatisFlexMinLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.LE, Operator.MIN_LE)
