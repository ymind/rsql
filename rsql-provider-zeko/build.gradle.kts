dependencies {
    api(project(":rsql-core"))

    api("io.zeko:zeko-sql-builder") {
        exclude(group = "io.zeko", module = "zeko-data-mapper")
        exclude(group = "io.vertx")
        exclude(group = "com.github.jasync-sql")
    }
}
