package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.*

class MybatisFlexCountEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.EQUALS, Operator.COUNT_EQ)
class MybatisFlexCountGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.GT, Operator.COUNT_GT)
class MybatisFlexCountLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.LT, Operator.COUNT_LT)
class MybatisFlexCountGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.GE, Operator.COUNT_GE)
class MybatisFlexCountLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.COUNT, SqlOperator.LE, Operator.COUNT_LE)
