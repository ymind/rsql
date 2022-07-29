package team.yi.rsql.zeko

import io.zeko.db.sql.QueryBlock
import team.yi.rsql.sqlEscape

fun isEmpty(field: String): QueryBlock {
    return QueryBlock("($field", " IS NULL OR $field =", "'')")
}

fun isNotEmpty(field: String): QueryBlock {
    return QueryBlock("($field", " IS NOT NULL AND $field !=", "'')")
}

fun eq(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "=", if (useRawValue) "'${value.sqlEscape()}'" else "?")
}

fun neq(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "!=", if (useRawValue) "'${value.sqlEscape()}'" else "?")
}

fun eqIC(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock("UPPER($field)", "=", if (useRawValue) "UPPER('${value.sqlEscape()}')" else "?")
}

fun neqIC(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock("UPPER($field)", "!=", if (useRawValue) "UPPER('${value.sqlEscape()}')" else "?")
}

fun likeIC(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock("UPPER($field)", "LIKE", if (useRawValue) "UPPER('${value.sqlEscape()}')" else "?")
}

fun startsWith(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "LIKE", if (useRawValue) "'${value.sqlEscape()}%'" else "?")
}

fun startsWithIC(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock("UPPER($field)", "LIKE", if (useRawValue) "UPPER('${value.sqlEscape()}%')" else "?")
}

fun endsWith(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "LIKE", if (useRawValue) "'%${value.sqlEscape()}'" else "?")
}

fun endsWithIC(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock("UPPER($field)", "LIKE", if (useRawValue) "UPPER('%${value.sqlEscape()}')" else "?")
}

fun contains(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "LIKE", if (useRawValue) "'%${value.sqlEscape()}%'" else "?")
}

fun containsIC(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock("UPPER($field)", "LIKE", if (useRawValue) "UPPER('%${value.sqlEscape()}%')" else "?")
}

fun notBetween(field: String, value1: String, value2: String, useRawValue: Boolean = false): QueryBlock {
    if (useRawValue) {
        return QueryBlock("$field NOT BETWEEN ", value1, " AND $value2")
    }

    return QueryBlock("$field NOT BETWEEN ", "?", " AND ?")
}

fun notBetween(field: String, value1: Int, value2: Int): QueryBlock {
    return QueryBlock("$field NOT BETWEEN ", "$value1", " AND $value2")
}

fun notBetween(field: String, value1: Long, value2: Long): QueryBlock {
    return QueryBlock("$field NOT BETWEEN ", "$value1", " AND $value2")
}

fun notBetween(field: String, value1: Double, value2: Double): QueryBlock {
    return QueryBlock("$field NOT BETWEEN ", "$value1", " AND $value2")
}

fun before(field: String, value: String): QueryBlock {
    return QueryBlock(field, "<", "'${value.sqlEscape()}'")
}

fun after(field: String, value: String): QueryBlock {
    return QueryBlock(field, ">", "'${value.sqlEscape()}'")
}
