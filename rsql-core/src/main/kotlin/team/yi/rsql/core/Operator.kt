package team.yi.rsql.core

enum class Operator(
    vararg val symbols: String,
    val multiValue: Boolean,
) {
    IS_NULL("=isNull=", multiValue = false),
    IS_NOT_NULL("=isNotNull=", "=notNull=", multiValue = false),

    IN("=in=", multiValue = true),
    NOT_IN("=notIn=", "=out=", multiValue = true),

    EQUALS("==", "=eq=", multiValue = false),
    NOT_EQUALS("!=", "=ne=", multiValue = false),

    IS_TRUE("=isTrue=", multiValue = false),
    IS_FALSE("=isFalse=", multiValue = false),

    IS_EMPTY("=isEmpty=", "=empty=", multiValue = false),
    IS_NOT_EMPTY("=isNotEmpty=", "=notEmpty=", multiValue = false),

    EQUALS_IGNORE_CASE("=equalsIgnoreCase=", "=eqic=", multiValue = false),
    NOT_EQUALS_IGNORE_CASE("=notEqualsIgnoreCase=", "=neqic=", multiValue = false),

    LIKE("=like=", multiValue = false),
    LIKE_IGNORE_CASE("=likeIgnoreCase=", "=likeic=", multiValue = false),
    NOT_LIKE("=notLike=", multiValue = false),

    REGEX("=regex=", multiValue = false),

    STARTS_WITH("=startsWith=", multiValue = false),
    STARTS_WITH_IGNORE_CASE("=startsWithIgnoreCase=", multiValue = false),
    ENDS_WITH("=endsWith=", multiValue = false),
    ENDS_WITH_IGNORE_CASE("=endsWithIgnoreCase=", multiValue = false),
    CONTAINS("=con=", "=contains=", multiValue = false),
    CONTAINS_IGNORE_CASE("=containsIgnoreCase=", "=conic=", multiValue = false),

    BETWEEN("=between=", multiValue = true),
    NOT_BETWEEN("=notBetween=", multiValue = true),

    GREATER_THAN(">", "=gt=", "=greater=", multiValue = false),
    GREATER_THAN_OR_EQUALS(">=", "=goe=", "=ge=", multiValue = false),

    LESS_THAN("<", "=lt=", multiValue = false),
    LESS_THAN_OR_EQUALS("<=", "=loe=", "=le=", multiValue = false),

    BEFORE("=before=", multiValue = false),
    AFTER("=after=", multiValue = false),

    // aggregations
    AVG_EQ("=avgEq=", multiValue = false),
    AVG_GT("=avgGt=", multiValue = false),
    AVG_LT("=avgLt=", multiValue = false),
    AVG_GE("=avgGe=", multiValue = false),
    AVG_LE("=avgLe=", multiValue = false),

    COUNT_EQ("=countEq=", multiValue = false),
    COUNT_GT("=countGt=", multiValue = false),
    COUNT_LT("=countLt=", multiValue = false),
    COUNT_GE("=countGe=", multiValue = false),
    COUNT_LE("=countLe=", multiValue = false),

    MAX_EQ("=maxEq=", multiValue = false),
    MAX_GT("=maxGt=", multiValue = false),
    MAX_LT("=maxLt=", multiValue = false),
    MAX_GE("=maxGe=", multiValue = false),
    MAX_LE("=maxLe=", multiValue = false),

    MIN_EQ("=minEq=", multiValue = false),
    MIN_GT("=minGt=", multiValue = false),
    MIN_LT("=minLt=", multiValue = false),
    MIN_GE("=minGe=", multiValue = false),
    MIN_LE("=minLe=", multiValue = false),

    SUM_EQ("=sumEq=", multiValue = false),
    SUM_GT("=sumGt=", multiValue = false),
    SUM_LT("=sumLt=", multiValue = false),
    SUM_GE("=sumGe=", multiValue = false),
    SUM_LE("=sumLe=", multiValue = false),

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
