package team.yi.rsql.test.mysql

import com.zaxxer.hikari.HikariConfig
import org.testcontainers.containers.MySQLContainer
import team.yi.rsql.test.BaseTest
import kotlin.concurrent.thread

abstract class BaseMySqlTest : BaseTest() {
    override val hikariConfig: HikariConfig
        get() = HikariConfig().also {
            it.driverClassName = driverClassName
            it.jdbcUrl = jdbcUrl
            it.username = username
            it.password = password
        }

    override val dbType: String = "mysql"

    companion object : MySQLContainer<Companion>("mysql:8") {
        init {
            start()

            Runtime.getRuntime().addShutdownHook(
                thread(start = false) {
                    stop()
                }
            )
        }
    }
}
