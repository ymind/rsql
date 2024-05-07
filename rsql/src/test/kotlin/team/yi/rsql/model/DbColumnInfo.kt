package team.yi.rsql.model

class DbColumnInfo(
    val catalogName: String,
    val schemaName: String,
    val tableName: String,
    val columnName: String,
    val columnLabel: String,
    val columnType: Int,
    val columnTypeName: String,
) {
    var isAutoIncrement: Boolean = false
    var isCaseSensitive: Boolean = false
    var isSearchable: Boolean = false
    var isCurrency: Boolean = false
    var isNullable: Int = 0
    var isSigned: Boolean = false
    var isReadOnly: Boolean = false
    var isWritable: Boolean = false
    var isDefinitelyWritable: Boolean = false

    lateinit var columnClassName: String
    var columnDisplaySize: Int = 0
    var precision: Int = 0
    var scale: Int = 0
}
