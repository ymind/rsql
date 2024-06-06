package team.yi.rsql.core

import cz.jirutka.rsql.parser.ast.Arity

data class Operator(
    val symbols: List<String>,
    val arity: Arity,
) {
    constructor(
        vararg symbols: String,
        arity: Arity = Arity.nary(1),
    ) : this(symbols.toList(), arity)

    init {
        symbols.forEach { symbol ->
            lookup[symbol] = this
        }

        entries.add(this)
    }

    companion object {
        val entries = mutableSetOf<Operator>()
        val lookup = mutableMapOf<String, Operator>()

        operator fun get(operator: String): Operator? = lookup[operator]

        val IS_NULL = Operator("=null=", "=isNull=")
        val IS_NOT_NULL = Operator("=isNotNull=", "=notNull=")

        val IN = Operator("=in=", arity = Arity.of(1, Int.MAX_VALUE))
        val NOT_IN = Operator("=notIn=", "=out=", arity = Arity.of(1, Int.MAX_VALUE))

        val EQUALS = Operator("==", "=eq=")
        val NOT_EQUALS = Operator("!=", "=ne=")

        val IS_TRUE = Operator("=is=", "=isTrue=")
        val IS_FALSE = Operator("=notIs=", "=isFalse=")

        val IS_EMPTY = Operator("=isEmpty=", "=empty=")
        val IS_NOT_EMPTY = Operator("=isNotEmpty=", "=notEmpty=")

        val EQUALS_IGNORE_CASE = Operator("=equalsIgnoreCase=", "=eqic=")
        val NOT_EQUALS_IGNORE_CASE = Operator("=notEqualsIgnoreCase=", "=neqic=")

        val LIKE = Operator("=like=")
        val LIKE_IGNORE_CASE = Operator("=likeIgnoreCase=", "=likeic=")
        val NOT_LIKE = Operator("=notLike=")

        val REGEX = Operator("=regex=")

        val STARTS_WITH = Operator("=startsWith=")
        val STARTS_WITH_IGNORE_CASE = Operator("=startsWithIgnoreCase=")
        val ENDS_WITH = Operator("=endsWith=")
        val ENDS_WITH_IGNORE_CASE = Operator("=endsWithIgnoreCase=")
        val CONTAINS = Operator("=con=", "=contains=")
        val CONTAINS_IGNORE_CASE = Operator("=containsIgnoreCase=", "=conic=")

        val BETWEEN = Operator("=between=", arity = Arity.nary(2))
        val NOT_BETWEEN = Operator("=notBetween=", arity = Arity.nary(2))

        val GREATER_THAN = Operator(">", "=gt=", "=greater=")
        val GREATER_THAN_OR_EQUALS = Operator(">=", "=goe=", "=ge=")

        val LESS_THAN = Operator("<", "=lt=")
        val LESS_THAN_OR_EQUALS = Operator("<=", "=loe=", "=le=")

        val BEFORE = Operator("=before=")
        val AFTER = Operator("=after=")

        // aggregations
        val AVG_EQ = Operator("=avgEq=")
        val AVG_GT = Operator("=avgGt=")
        val AVG_LT = Operator("=avgLt=")
        val AVG_GE = Operator("=avgGe=")
        val AVG_LE = Operator("=avgLe=")

        val COUNT_EQ = Operator("=countEq=")
        val COUNT_GT = Operator("=countGt=")
        val COUNT_LT = Operator("=countLt=")
        val COUNT_GE = Operator("=countGe=")
        val COUNT_LE = Operator("=countLe=")

        val MAX_EQ = Operator("=maxEq=")
        val MAX_GT = Operator("=maxGt=")
        val MAX_LT = Operator("=maxLt=")
        val MAX_GE = Operator("=maxGe=")
        val MAX_LE = Operator("=maxLe=")

        val MIN_EQ = Operator("=minEq=")
        val MIN_GT = Operator("=minGt=")
        val MIN_LT = Operator("=minLt=")
        val MIN_GE = Operator("=minGe=")
        val MIN_LE = Operator("=minLe=")

        val SUM_EQ = Operator("=sumEq=")
        val SUM_GT = Operator("=sumGt=")
        val SUM_LT = Operator("=sumLt=")
        val SUM_GE = Operator("=sumGe=")
        val SUM_LE = Operator("=sumLe=")
    }
}
