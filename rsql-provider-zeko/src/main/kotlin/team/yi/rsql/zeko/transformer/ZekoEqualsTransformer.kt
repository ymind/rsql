package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.core.*
import team.yi.rsql.zeko.*

class ZekoEqualsTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer(Operator.EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = eq(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoNotEqualsTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer(Operator.NOT_EQUALS) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = neq(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoEqualsIgnoreCaseTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer(Operator.EQUALS_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = eqIC(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoNotEqualsIgnoreCaseTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer(Operator.NOT_EQUALS_IGNORE_CASE) {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = neqIC(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}
