package team.yi.rsql.mybatisflex.transformer

import com.mybatisflex.core.query.QueryCondition
import team.yi.rsql.RsqlTransformer
import team.yi.rsql.core.Operator

abstract class MybatisFlexRsqlTransformer(
    override val operator: Operator,
) : RsqlTransformer<QueryCondition>
