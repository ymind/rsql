package team.yi.rsql.core

import cz.jirutka.rsql.parser.ast.Arity

enum class Operator(
    vararg val symbols: String,
    val arity: Arity = Arity.nary(1),
) {
    IS_NULL("=null=", "=isNull="),
    IS_NOT_NULL("=isNotNull=", "=notNull="),

    IN("=in=", arity = Arity.of(1, Int.MAX_VALUE)),
    NOT_IN("=notIn=", "=out=", arity = Arity.of(1, Int.MAX_VALUE)),

    EQUALS("==", "=eq="),
    NOT_EQUALS("!=", "=ne="),

    IS_TRUE("=is=", "=isTrue="),
    IS_FALSE("=notIs=", "=isFalse="),

    IS_EMPTY("=isEmpty=", "=empty="),
    IS_NOT_EMPTY("=isNotEmpty=", "=notEmpty="),

    EQUALS_IGNORE_CASE("=equalsIgnoreCase=", "=eqic="),
    NOT_EQUALS_IGNORE_CASE("=notEqualsIgnoreCase=", "=neqic="),

    LIKE("=like="),
    LIKE_IGNORE_CASE("=likeIgnoreCase=", "=likeic="),
    NOT_LIKE("=notLike="),

    REGEX("=regex="),

    STARTS_WITH("=startsWith="),
    STARTS_WITH_IGNORE_CASE("=startsWithIgnoreCase="),
    ENDS_WITH("=endsWith="),
    ENDS_WITH_IGNORE_CASE("=endsWithIgnoreCase="),
    CONTAINS("=con=", "=contains="),
    CONTAINS_IGNORE_CASE("=containsIgnoreCase=", "=conic="),

    BETWEEN("=between=", arity = Arity.nary(2)),
    NOT_BETWEEN("=notBetween=", arity = Arity.nary(2)),

    GREATER_THAN(">", "=gt=", "=greater="),
    GREATER_THAN_OR_EQUALS(">=", "=goe=", "=ge="),

    LESS_THAN("<", "=lt="),
    LESS_THAN_OR_EQUALS("<=", "=loe=", "=le="),

    BEFORE("=before="),
    AFTER("=after="),

    // aggregations
    AVG_EQ("=avgEq="),
    AVG_GT("=avgGt="),
    AVG_LT("=avgLt="),
    AVG_GE("=avgGe="),
    AVG_LE("=avgLe="),

    COUNT_EQ("=countEq="),
    COUNT_GT("=countGt="),
    COUNT_LT("=countLt="),
    COUNT_GE("=countGe="),
    COUNT_LE("=countLe="),

    MAX_EQ("=maxEq="),
    MAX_GT("=maxGt="),
    MAX_LT("=maxLt="),
    MAX_GE("=maxGe="),
    MAX_LE("=maxLe="),

    MIN_EQ("=minEq="),
    MIN_GT("=minGt="),
    MIN_LT("=minLt="),
    MIN_GE("=minGe="),
    MIN_LE("=minLe="),

    SUM_EQ("=sumEq="),
    SUM_GT("=sumGt="),
    SUM_LT("=sumLt="),
    SUM_GE("=sumGe="),
    SUM_LE("=sumLe="),

    // end
    ;

    companion object {
        val lookup = mutableMapOf<String, Operator>()

        operator fun get(operator: String): Operator? = lookup[operator]

        init {
            entries.forEach {
                it.symbols.forEach { symbol ->
                    lookup[symbol] = it
                }
            }
        }
    }
}
