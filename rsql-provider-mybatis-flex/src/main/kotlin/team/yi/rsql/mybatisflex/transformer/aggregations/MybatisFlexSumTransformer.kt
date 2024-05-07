package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.*

class MybatisFlexSumEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.EQUALS, Operator.SUM_EQ)
class MybatisFlexSumGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.GT, Operator.SUM_GT)
class MybatisFlexSumLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.LT, Operator.SUM_LT)
class MybatisFlexSumGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.GE, Operator.SUM_GE)
class MybatisFlexSumLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.LE, Operator.SUM_LE)
