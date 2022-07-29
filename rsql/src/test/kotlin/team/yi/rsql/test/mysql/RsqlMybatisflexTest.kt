package team.yi.rsql.test.mysql

import com.mybatisflex.core.MybatisFlexBootstrap
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.core.table.TableDef
import com.zaxxer.hikari.HikariDataSource
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import team.yi.rsql.*

class RsqlMybatisflexTest : BaseMySqlTest() {
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
            this.from = "t_employee"
            this.groupBy = listOf("DEPARTMENT_ID")
            this.where = ""
            this.having = "SALARY=countGt=1"
            this.orderBy = listOf("COUNT(*) DESC")
        }

        // val dataSource = HikariDataSource(hikariConfig)
        val queryWrapper = QueryWrapper()
        val rsql = Rsql.myBatisFlex(queryWrapper)
        val rsqlQuery = rsql.build(rsqlInput)
        val query = rsqlQuery.resultQuery

        val sql = query.toSQL()

        kLogger.info { "query:\n$sql" }

        runBlocking {
            val queryResult = execute(sql)

            print(queryResult)
        }
    }

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

        // SELECT DISTINCT
        //      uuid() UUID, e.NAME 姓名, d.NAME 部门名称, e.JOB 岗位, e.MANAGER_ID 经理ID, e.HIRE_DATE 聘用日期,
        //      e.SALARY 薪水, d.ID 部门ID, d.LOCATION 地址
        // FROM `t_employee` AS `e`
        //      LEFT JOIN `t_department` AS `d` ON  e.DEPARTMENT_ID = d.ID
        // WHERE
        //      e.SALARY >= 10
        //      AND e.NAME IS NOT NULL
        //      AND (
        //          e.MANAGER_ID IS NULL
        //          OR (
        //              e.DEPARTMENT_ID NOT IN ('1151', '1152')
        //              AND (
        //                  d.NAME != 'else'
        //                  OR e.JOB IN ('assistant', 'director', 'trainee', 'engineer')
        //              )
        //          )
        //      )
        //      AND e.HIRE_DATE < '2019-01-01'
        // ORDER BY d.ID ASC, e.SALARY DESC
        // LIMIT 10, 0

        val rsqlInput = RsqlInput().apply {
            this.distinct = true
            this.fields = fields
            this.from = "t_employee e"
            this.where =
                """
                    e.SALARY>='10'
                    and e.NAME=isNotNull=1
                    and (
                        e.MANAGER_ID=isNull=1 or e.DEPARTMENT_ID=out=(1151, 1152)
                        and (
                            d.NAME!='else' or e.JOB=in=(assistant,director,trainee,engineer)
                        )
                    )
                    and e.HIRE_DATE=before='2019-01-01'
                """.trimIndent().replace("\n", " ")
            this.orderBy = listOf("d.ID asc", "e.SALARY desc")
            this.limit = 10
            this.offset = 0
        }

        val dataSource = HikariDataSource(hikariConfig)

        MybatisFlexBootstrap.getInstance()
            .setDataSource(dataSource)
            .start()

        val queryWrapper = QueryWrapper()
        val rsql = Rsql.myBatisFlex(queryWrapper)
        val rsqlQuery = rsql.build(rsqlInput)

        val query = rsqlQuery.resultQuery

        query.select("d.LOCATION 地址")

        val dTable = TableDef("t_department")

        query.leftJoin<QueryWrapper>(dTable).`as`("d").on("e.DEPARTMENT_ID = d.ID")

        val sql = query.toSQL()

        kLogger.info { "query:\n$sql" }

        runBlocking {
            val queryResult = execute(sql)

            print(queryResult)
        }
    }
}
