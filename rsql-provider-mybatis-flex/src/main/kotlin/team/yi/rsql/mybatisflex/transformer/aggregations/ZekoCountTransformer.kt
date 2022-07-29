package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.SqlAggregation

class MybatisFlexCountEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.EQUALS)
class MybatisFlexCountGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.GT)
class MybatisFlexCountLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.LT)
class MybatisFlexCountGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.GE)
class MybatisFlexCountLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.LE)
