package team.yi.rsql.test.h2

import com.zaxxer.hikari.HikariDataSource
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import org.jooq.*
import org.jooq.conf.*
import org.jooq.impl.*
import org.junit.jupiter.api.Test
import team.yi.rsql.*

class RsqlJooqTest : H2BaseTest() {
    private val kLogger = KotlinLogging.logger {}

    @Test
    fun `aggregations with group by`() {
        val fields = listOf(
            "DEPARTMENT_ID 部门",
            "COUNT(*) 员工数量",
            "MAX(HIRE_DATE) 最晚聘用日期",
        )
        val rsqlInput = RsqlInput().apply {
            this.distinct = true
            this.fields = fields
            this.from = "T_EMPLOYEE"
            this.groupBy = listOf("DEPARTMENT_ID")
            this.where = ""
            this.having = "SALARY=countGt=1"
            this.orderBy = listOf("COUNT(*) DESC")
        }

        val dataSource = HikariDataSource(hikariConfig)
        val create = DSL.using(dataSource, SQLDialect.H2)
        val rsql = Rsql.jooq(create)
        val rsqlQuery = rsql.build(rsqlInput)
        val query = rsqlQuery.resultQuery

        val sql = query.getSQL(ParamType.NAMED_OR_INLINED)

        kLogger.info { "query:\n$sql" }

        runBlocking {
            val queryResult = execute(sql)

            print(queryResult)
        }
    }

    @Suppress("UnstableApiUsage")
    @Test
    fun `normal query`() {
        val fields = listOf(
            "uuid() UUID",
            "e.NAME 姓名",
            "d.NAME 部门名称",
            "e.JOB 岗位",
            "e.MANAGER_ID 经理ID",
            "e.HIRE_DATE 聘用日期",
            "e.SALARY 薪水",
            "d.ID 部门ID",
        )

        val rsqlInput = RsqlInput().apply {
            this.distinct = true
            this.fields = fields
            this.from = "T_EMPLOYEE e"
            this.where =
                """
                    e.SALARY@num>='10'
                    and e.NAME=isNotNull=1
                    and (
                        e.MANAGER_ID=isNull=1 or e.DEPARTMENT_ID=out=('raw#__',1151, 1152)
                        and (
                            d.NAME!='else' or e.JOB=in=('assistant',director,"trainee",'engineer')
                        )
                    )
                    and e.HIRE_DATE=before='2019-01-01'
                """.trimIndent().replace("\n", " ")
            this.orderBy = listOf("d.ID asc", "e.SALARY desc")
            this.limit = 10
            this.offset = 0
        }

        val dataSource = HikariDataSource(hikariConfig)
        val settings = Settings().apply {
            this.withRenderFormatted(true)
            this.withExecuteLogging(true)
            this.withRenderCatalog(true)
            this.withRenderSchema(true)

            this.withRenderMapping(
                RenderMapping()
                    .withDefaultCatalog("rsql_db_test1")
                    .withCatalogs(
                        MappedCatalog().withInput("DEV1").withOutput("MY_BOOK_WORLD"),
                        MappedCatalog().withInput("LOG1").withOutput("MY_BOOK_WORLD_LOG")
                    )
                    .withDefaultSchema("rsql_db_test2")
                    .withSchemata(
                        MappedSchema().withInput("DEV2").withOutput("MY_BOOK_WORLD"),
                        MappedSchema().withInput("LOG2").withOutput("MY_BOOK_WORLD_LOG")
                    )
            )
        }
        val configuration = DefaultConfiguration().apply {
            this.set(dataSource)
            this.set(settings)
            // this.set(DefaultVisitListenerProvider(BindValueAbbreviator()))
            this.set(DefaultExecuteListenerProvider(KmDefaultExecuteListener()))
        }

        val create = DSL.using(configuration)
        val rsql = Rsql.jooq(create)
        val rsqlQuery = rsql.build(rsqlInput)

        val query = rsqlQuery.resultQuery as SelectJoinStep<*>
        val fieldList = fields.map { DSL.field(it) }.toMutableList()
        fieldList.add(2, DSL.field("d.LOCATION 地址"))

        val department = DSL.table("T_DEPARTMENT d")

        query.leftJoin(department).on(
            DSL.field("e.DEPARTMENT_ID").eq(DSL.field("d.ID"))
        )

        query.`$select`(fieldList).also {
            val result = it.fetchResultSet()
            val queryResult = convert(result)

            print(queryResult)
        }
    }
}
