package team.yi.rsql.zeko.transformer

import io.zeko.db.sql.QueryBlock
import io.zeko.db.sql.operators.*
import team.yi.rsql.core.RsqlQueryPart
import team.yi.rsql.zeko.*

class ZekoLikeTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = like(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoLikeIgnoreCaseTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = likeIC(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoNotLikeTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = notLike(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoStartsWithTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = startsWith(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoStartsWithIgnoreCaseTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = startsWithIC(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoEndsWithTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = endsWith(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoEndsWithIgnoreCaseTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = endsWithIC(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoContainsTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = contains(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}

class ZekoContainsIgnoreCaseTransformer(private val useRawValue: Boolean) : ZekoRsqlTransformer() {
    override fun transform(selector: String, arguments: List<String>, typePrompt: String?): RsqlQueryPart<QueryBlock> {
        val value1 = arguments[0]
        val queryBlock = containsIC(selector, value1, useRawValue)

        return RsqlQueryPart(selector, arguments, queryBlock, useRawValue, value1)
    }
}
