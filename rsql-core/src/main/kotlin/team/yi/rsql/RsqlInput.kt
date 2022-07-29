package team.yi.rsql

class RsqlInput {
    var distinct: Boolean? = null
    var fields: List<String>? = null
    var from: String? = null
    var groupBy: List<String>? = null
    var where: String? = null
    var having: String? = null
    var orderBy: List<String>? = null
    var offset: Long? = null
    var limit: Long? = null
}
