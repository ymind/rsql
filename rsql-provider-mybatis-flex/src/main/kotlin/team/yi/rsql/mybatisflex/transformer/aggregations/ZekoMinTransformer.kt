package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.SqlAggregation

class MybatisFlexMinEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.EQUALS)
class MybatisFlexMinGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.GT)
class MybatisFlexMinLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.LT)
class MybatisFlexMinGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.GE)
class MybatisFlexMinLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MIN, SqlOperator.LE)
