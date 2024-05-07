package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.*

class MybatisFlexMaxEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.EQUALS, Operator.MAX_EQ)
class MybatisFlexMaxGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.GT, Operator.MAX_GT)
class MybatisFlexMaxLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.LT, Operator.MAX_LT)
class MybatisFlexMaxGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.GE, Operator.MAX_GE)
class MybatisFlexMaxLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.LE, Operator.MAX_LE)
