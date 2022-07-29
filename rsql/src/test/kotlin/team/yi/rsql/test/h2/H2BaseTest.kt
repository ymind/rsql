package team.yi.rsql.test.h2

import com.zaxxer.hikari.HikariConfig
import team.yi.rsql.test.BaseTest

open class H2BaseTest : BaseTest() {
    override val hikariConfig: HikariConfig
        get() = HikariConfig().apply {
            this.driverClassName = "org.h2.Driver"
            this.jdbcUrl = "jdbc:h2:mem:rsql_db_test;DB_CLOSE_DELAY=-1;"
        }
}
