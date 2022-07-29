package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.SqlAggregation

class MybatisFlexSumEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.EQUALS)
class MybatisFlexSumGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.GT)
class MybatisFlexSumLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.LT)
class MybatisFlexSumGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.GE)
class MybatisFlexSumLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.SUM, SqlOperator.LE)
