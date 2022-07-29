package team.yi.rsql

import team.yi.rsql.core.RsqlQueryPart

data class RsqlOutput<Q, C, R>(
    val resultQuery: Q,
    val countQuery: C?,
    val queryParts: List<RsqlQueryPart<R>>,
)
