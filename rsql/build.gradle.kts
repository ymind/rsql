dependencies {
    api(project(":rsql-core"))

    providerImplementation(project(":rsql-provider-jooq"))
    providerImplementation(project(":rsql-provider-zeko"))
    providerImplementation(project(":rsql-provider-mybatis-flex"))

    testImplementation(project(":kt-ascii-table"))
    testImplementation("com.zaxxer:HikariCP")
    testImplementation("com.h2database:h2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8")

    // https://mvnrepository.com/artifact/team.yi.jacksync/jacksync
    testImplementation("team.yi.jacksync:jacksync:0.8.16")

    // https://mvnrepository.com/artifact/org.dromara.hutool/hutool-all
    testImplementation("org.dromara.hutool:hutool-all:6.0.0-M13")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    testImplementation("com.fasterxml.jackson.core:jackson-databind")

    // https://mvnrepository.com/artifact/org.testcontainers/mysql
    testImplementation("org.testcontainers:mysql:1.19.8")
    testImplementation("org.testcontainers:junit-jupiter:1.19.8")

    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    testImplementation("com.mysql:mysql-connector-j:8.4.0")
}
