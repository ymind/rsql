package team.yi.rsql.test.mysql

import com.zaxxer.hikari.HikariConfig
import org.junit.jupiter.api.*
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import team.yi.rsql.test.BaseTest

abstract class BaseMySqlTest : BaseTest() {
    @Container
    protected val mysqlContainer = MySQLContainer("mysql:8")

    final override lateinit var hikariConfig: HikariConfig
        private set

    override val dbType: String = "mysql"

    @BeforeAll
    override fun init() {
        mysqlContainer.start()

        hikariConfig = HikariConfig().also {
            it.driverClassName = mysqlContainer.driverClassName
            it.jdbcUrl = mysqlContainer.jdbcUrl
            it.username = mysqlContainer.username
            it.password = mysqlContainer.password
        }

        super.init()
    }

    @AfterAll
    override fun destroy() {
        super.destroy()

        mysqlContainer.stop()
    }
}
