package team.yi.rsql.mybatisflex.transformer.aggregations

import com.mybatisflex.core.constant.SqlOperator
import team.yi.rsql.core.*

class MybatisFlexAvgEqTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.EQUALS, Operator.AVG_EQ)
class MybatisFlexAvgGtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.GT, Operator.AVG_GT)
class MybatisFlexAvgLtTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.LT, Operator.AVG_LT)
class MybatisFlexAvgGeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.GE, Operator.AVG_GE)
class MybatisFlexAvgLeTransformer : MybatisFlexAggregationTransformer(SqlAggregation.AVG, SqlOperator.LE, Operator.AVG_LE)
