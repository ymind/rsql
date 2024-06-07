plugins {
    `java-platform`

    id("se.patrikerdes.use-latest-versions")
    id("com.github.ben-manes.versions")
}

javaPlatform {
    allowDependencies()
}

dependencies {
    // https://mvnrepository.com/artifact/com.fasterxml.jackson/jackson-bom
    api(platform("com.fasterxml.jackson:jackson-bom:2.17.1"))
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-bom
    api(platform("org.apache.logging.log4j:log4j-bom:3.0.0-beta2"))
    // https://mvnrepository.com/artifact/org.jooq/jooq-parent
    api(platform("org.jooq:jooq-parent:3.19.9"))
    // https://mvnrepository.com/artifact/com.mybatis-flex/mybatis-flex-dependencies
    api(platform("com.mybatis-flex:mybatis-flex-dependencies:1.9.2"))

    constraints {
        // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
        api("ch.qos.logback:logback-classic:1.5.6")

        // https://mvnrepository.com/artifact/io.github.nstdio/rsql-parser
        api("io.github.nstdio:rsql-parser:2.3.2")
        // https://mvnrepository.com/artifact/io.github.oshai/kotlin-logging-jvm
        api("io.github.oshai:kotlin-logging-jvm:6.0.9")
        // https://mvnrepository.com/artifact/io.zeko/zeko-sql-builder
        api("io.zeko:zeko-sql-builder:1.5.6")
        // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-jdk8
        api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.8.1")

        // https://mvnrepository.com/artifact/com.h2database/h2
        api("com.h2database:h2:2.2.224")
        // https://mvnrepository.com/artifact/com.zaxxer/HikariCP
        api("com.zaxxer:HikariCP:5.1.0")
    }
}

repositories {
    mavenLocal()

    maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    maven("https://ymind-maven.pkg.coding.net/repository/emtboot/public/")
}
