package team.yi.rsql.test.h2

import io.github.oshai.kotlinlogging.KotlinLogging
import io.zeko.db.sql.operators.eq
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import team.yi.rsql.*

class RsqlZekoTest : H2BaseTest() {
    private val kLogger = KotlinLogging.logger {}

    @Test
    fun `aggregations with group by`() {
        val fields = listOf(
            "DEPARTMENT_ID 部门",
            "COUNT(*) 员工数量",
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

        val rsql = Rsql.zeko()
        val rsqlQuery = rsql.build(rsqlInput)
        val query = rsqlQuery.resultQuery

        val sql = query.toSql(true)

        kLogger.info { "query:\n$sql" }

        runBlocking {
            val queryResult = execute(sql)

            print(queryResult)
        }
    }

    @Test
    fun `normal query`() {
        val fields = listOf(
            "e.NAME 姓名",
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

        val rsql = Rsql.zeko()
        val rsqlQuery = rsql.build(rsqlInput)

        val params = rsqlQuery.queryParts.filter { !it.useRawValue }.map { it.parameters }.toMutableList()
        val query = rsqlQuery.resultQuery
        query.leftJoin("T_DEPARTMENT d").on(
            eq("e.DEPARTMENT_ID", "d.ID", true)
        )

        fields.toMutableList().also {
            it.add(0, "d.NAME")
        }.also {
            query.fields(*it.toTypedArray())
        }

        val sql = query.toSql(true).replace('`', '"')

        kLogger.info { "query:\n$sql" }

        runBlocking {
            val queryResult = execute(sql, params.flatten())

            print(queryResult)
        }

        val countSql = rsqlQuery.countQuery

        kLogger.info { "countSql:\n${countSql?.compile()}" }
    }
}
