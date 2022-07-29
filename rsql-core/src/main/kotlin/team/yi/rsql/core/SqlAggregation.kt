package team.yi.rsql.core

enum class SqlAggregation(val functionName: String) {
    SUM("SUM"),
    COUNT("COUNT"),
    AVG("AVG"),
    MIN("MIN"),
    MAX("MAX"),
}
