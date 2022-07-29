package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.SqlAggregation

class MybatisFlexAvgEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.EQUALS)
class MybatisFlexAvgGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.GT)
class MybatisFlexAvgLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.LT)
class MybatisFlexAvgGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.GE)
class MybatisFlexAvgLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.LE)
