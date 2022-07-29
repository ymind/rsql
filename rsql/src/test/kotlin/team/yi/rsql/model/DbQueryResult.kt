package team.yi.rsql.model

class DbQueryResult(
    val columns: List<DbColumnInfo>,
    val rowData: List<Map<String, Any?>>,
)
