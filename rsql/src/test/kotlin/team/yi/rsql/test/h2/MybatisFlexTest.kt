package team.yi.rsql.test.h2

import com.mybatisflex.core.query.*
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import team.yi.rsql.*

class MybatisFlexTest : H2BaseTest() {
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

        // val c1 = RawQueryColumn("id1").eq(1)
        // val c2 = RawQueryColumn("id2").eq(2)
        // val c3 = RawQueryColumn("id3").eq(3)
        //
        // val c4 = RawQueryColumn("title").eq("xxxx")
        //
        // val d1 = QueryCondition.createEmpty().and(c1).or(c2).or(c3)
        // val d3 = QueryCondition.createEmpty().and(d1).and(c4)
        // // SELECT * FROM `oooooo` WHERE title = 'xxxx' AND (id1 = 1 OR id2 = 2 OR id3 = 3)
        // val q = QueryWrapper.create()
        //     .from("oooooo")
        //     .where(d3)
        //     .toSQL()

        val queryWrapper = QueryWrapper.create()
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
                            d.NAME=='else' or e.JOB=in=(assistant,director,trainee,engineer)
                        )
                    )
                    and e.HIRE_DATE=before='2019-01-01'
                """.trimIndent().replace("\n", " ")
            this.orderBy = listOf("d.ID asc", "e.SALARY desc")
            this.limit = 10
            this.offset = 0
        }

        val queryWrapper = QueryWrapper.create()
        val rsql = Rsql.myBatisFlex(queryWrapper)
        val rsqlQuery = rsql.build(rsqlInput)

        val params = rsqlQuery.queryParts.filter { !it.useRawValue }.map { it.parameters }.toMutableList()
        val query = rsqlQuery.resultQuery

        val tableEmployee = QueryTable("T_EMPLOYEE").`as`("e")
        val tableDepartment = QueryTable("T_DEPARTMENT")

        query.leftJoin<QueryWrapper>(tableDepartment).`as`("d")
            .on(
                QueryColumn(tableEmployee, "DEPARTMENT_ID").eq(
                    QueryColumn(tableDepartment.`as`("d"), "ID")
                )
            )

        query.select("d.NAME")
        // query.resetSelect(...[new columns])

        val sql = query.toSQL()

        kLogger.info { "query:\n$sql" }

        runBlocking {
            val queryResult = execute(sql, params.flatten())

            print(queryResult)
        }

        val countSql = rsqlQuery.countQuery

        kLogger.info { "countSql:\n${countSql?.toSQL()}" }
    }
}
