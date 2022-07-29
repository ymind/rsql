package team.yi.rsql.test

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.pool.HikariProxyResultSet
import io.github.oshai.kotlinlogging.KotlinLogging
import io.zeko.db.sql.connections.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import team.yi.rsql.model.*
import team.yi.tools.ktables.*
import java.sql.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
    private val kLogger = KotlinLogging.logger {}

    protected lateinit var dbPool: HikariDBPool

    abstract val hikariConfig: HikariConfig

    open val dbType: String = "h2"

    @BeforeAll
    open fun init() {
        dbPool = HikariDBPool(hikariConfig).also {
            it.setInsertStatementMode(Statement.RETURN_GENERATED_KEYS)
        }

        runBlocking {
            execSqlScript("$dbType/init-data.sql")
        }
    }

    @AfterAll
    open fun destroy() {
        runBlocking {
            execSqlScript("$dbType/drop-data.sql")
        }
    }

    private fun readSqlScript(filename: String): String {
        return javaClass.classLoader?.getResourceAsStream(filename)?.bufferedReader()?.readText() ?: ""
    }

    protected suspend fun execSqlScript(filename: String) {
        val sqlScript = readSqlScript(filename)

        getDbSession().once { session ->
            for (sql in sqlScript.split(';')) {
                if (sql.any { it.isLetterOrDigit() }) {
                    session.update(sql, emptyList())
                }
            }
        }
    }

    protected suspend fun getDbSession(): HikariDBSession {
        return HikariDBSession(dbPool, dbPool.createConnection())
    }

    protected suspend fun execute(sql: String, params: List<Any?> = emptyList()): DbQueryResult {
        return getDbSession().once { session ->
            val queryResult = if (params.isEmpty()) {
                session.query(sql)
            } else {
                session.queryPrepared(sql, params)
            }

            val resultSet = queryResult as HikariProxyResultSet

            convert(resultSet)
        }
    }

    protected fun convert(resultSet: ResultSet): DbQueryResult {
        val metaData = resultSet.metaData
        val rowData = mutableListOf<Map<String, Any?>>()

        val columns = mutableListOf<DbColumnInfo>()

        for (i in 1..metaData.columnCount) {
            val column = DbColumnInfo(
                metaData.getCatalogName(i),
                metaData.getSchemaName(i),
                metaData.getTableName(i),
                metaData.getColumnName(i),
                metaData.getColumnLabel(i),
                metaData.getColumnType(i),
                metaData.getColumnTypeName(i),
            )

            column.isAutoIncrement = metaData.isAutoIncrement(i)
            column.isCaseSensitive = metaData.isCaseSensitive(i)
            column.isSearchable = metaData.isSearchable(i)
            column.isCurrency = metaData.isCurrency(i)
            column.isNullable = metaData.isNullable(i)
            column.isSigned = metaData.isSigned(i)
            column.isReadOnly = metaData.isReadOnly(i)
            column.isWritable = metaData.isWritable(i)
            column.isDefinitelyWritable = metaData.isDefinitelyWritable(i)

            column.columnClassName = metaData.getColumnClassName(i)
            column.columnDisplaySize = metaData.getColumnDisplaySize(i)
            column.precision = metaData.getPrecision(i)
            column.scale = metaData.getScale(i)

            columns.add(column)
        }

        while (resultSet.next()) {
            val item = mutableMapOf<String, Any?>()

            for (i in 1..metaData.columnCount) {
                item[metaData.getColumnLabel(i)] = resultSet.getObject(i)
            }

            rowData.add(item)
        }

        return DbQueryResult(columns, rowData)
    }

    protected fun print(queryResult: DbQueryResult) {
        val items = queryResult.rowData

        kLogger.info { "items: ${items.size}" }

        if (items.isEmpty()) return

        kLogger.info {
            val headers = Header(
                queryResult.columns.map {
                    val columnClass = Class.forName(it.columnClassName)
                    val align = when {
                        Number::class.java.isAssignableFrom(columnClass) -> TableAlignment.RIGHT
                        Boolean::class.java.isAssignableFrom(columnClass) -> TableAlignment.CENTER
                        else -> TableAlignment.LEFT
                    }

                    Cell(it.columnLabel, align)
                },
            )
            val values = items.map { Row(it.values.map { v -> Cell(v?.toString() ?: "") }) }

            "results:\n" + Table(headers, values).build()
        }
    }
}
