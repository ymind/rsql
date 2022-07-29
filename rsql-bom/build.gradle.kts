plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    // api(platform(project(":rsql-deps")))

    constraints {
        api(project(":kt-ascii-table"))

        api(project(":rsql"))
        api(project(":rsql-core"))
        api(project(":rsql-provider-jooq"))
        api(project(":rsql-provider-zeko"))
        api(project(":rsql-provider-mybatis-flex"))
    }
}
