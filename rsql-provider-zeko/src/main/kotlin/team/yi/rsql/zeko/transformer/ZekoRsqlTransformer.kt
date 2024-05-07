package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.RsqlTransformer
import team.yi.rsql.core.Operator

@Suppress("UnnecessaryAbstractClass")
abstract class ZekoRsqlTransformer(
    override val operator: Operator,
) : RsqlTransformer<QueryBlock>
