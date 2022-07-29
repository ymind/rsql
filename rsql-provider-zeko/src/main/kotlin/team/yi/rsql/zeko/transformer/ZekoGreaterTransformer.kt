package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.operators.*
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.zeko.*

class ZekoGreaterTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = greater(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class ZekoGreaterEqualsTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = greaterEq(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class ZekoLessTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = less(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class ZekoLessEqualsTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = lessEq(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class ZekoBetweenTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val value2 = arguments[1]
        val queryBlock = between(selector, value1, value2, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1, value2)
    }
}

class ZekoNotBetweenTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val value2 = arguments[1]
        val queryBlock = notBetween(selector, value1, value2, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1, value2)
    }
}

class ZekoBeforeTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = before(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}

class ZekoAfterTransformer : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = after(selector, value1)

        return RsqlQueryPart(selector, arguments, queryBlock, true, value1)
    }
}
