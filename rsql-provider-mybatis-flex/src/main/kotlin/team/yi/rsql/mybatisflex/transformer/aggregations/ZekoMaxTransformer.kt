package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.SqlAggregation

class MybatisFlexMaxEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.EQUALS)
class MybatisFlexMaxGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.GT)
class MybatisFlexMaxLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.LT)
class MybatisFlexMaxGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.GE)
class MybatisFlexMaxLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.MAX, SqlOperator.LE)
