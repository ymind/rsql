package team.yi.rsql.test.h2

import com.zaxxer.hikari.pool.HikariProxyResultSet
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class ZekoAutoApiTest : H2BaseTest() {
    private val kLogger = KotlinLogging.logger {}

    @Test
    fun post() {
        runBlocking {
            getDbSession().once { operation ->
                val querySql = "select 1, 2, 3"
                val q = operation.query(querySql)
                val dbQueryResult = convert(q as HikariProxyResultSet)
                kLogger.info { "$dbQueryResult" }

                val insertSql = "insert into \"T_DEPARTMENT\"(\"NAME\", \"LOCATION\") values ('tech', 'Guangzhou');"
                val i = operation.insert(insertSql, listOf())
                kLogger.info { "$i" }

                val updateSql = """update T_DEPARTMENT set NAME='xxx'"""
                val u = operation.update(updateSql, listOf())
                kLogger.info { "$u" }

                val deleteSql = """delete from T_DEPARTMENT"""
                val d = operation.update(deleteSql, listOf())
                kLogger.info { "$d" }
            }
        }
    }
}
