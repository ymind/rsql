pluginManagement {
    val kotlinVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

rootProject.name = "rsql"

include("rsql-bom", "rsql-deps")

include("rsql")
include("rsql-core")
include("rsql-provider-zeko", "rsql-provider-jooq", "rsql-provider-mybatis-flex")

include("kt-ascii-table")
