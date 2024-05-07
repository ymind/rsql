package team.yi.rsql.jooq.transformer

import org.jooq.QueryPart
import team.yi.rsql.RsqlTransformer
import team.yi.rsql.core.Operator

@Suppress("UnnecessaryAbstractClass")
abstract class JooqRsqlTransformer(
    override val operator: Operator,
) : RsqlTransformer<QueryPart>
