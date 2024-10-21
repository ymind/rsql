plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    // https://mvnrepository.com/artifact/com.fasterxml.jackson/jackson-bom
    api(platform("com.fasterxml.jackson:jackson-bom:2.18.2"))
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-bom
    api(platform("org.apache.logging.log4j:log4j-bom:3.0.0-beta3"))
    // https://mvnrepository.com/artifact/org.jooq/jooq-parent
    api(platform("org.jooq:jooq-parent:3.19.17"))
    // https://mvnrepository.com/artifact/com.mybatis-flex/mybatis-flex-dependencies
    api(platform("com.mybatis-flex:mybatis-flex-dependencies:1.10.5"))

    constraints {
        // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
        api("ch.qos.logback:logback-classic:1.5.16")

        // https://mvnrepository.com/artifact/io.github.nstdio/rsql-parser
        api("io.github.nstdio:rsql-parser:2.3.3")
        // https://mvnrepository.com/artifact/io.github.oshai/kotlin-logging-jvm
        api("io.github.oshai:kotlin-logging-jvm:7.0.3")
        // https://mvnrepository.com/artifact/io.zeko/zeko-sql-builder
        api("io.zeko:zeko-sql-builder:1.5.6")
        // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-jdk8
        api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.10.1")

        // https://mvnrepository.com/artifact/com.h2database/h2
        api("com.h2database:h2:2.3.232")
        // https://mvnrepository.com/artifact/com.zaxxer/HikariCP
        api("com.zaxxer:HikariCP:6.2.1")
    }
}

repositories {
    mavenLocal()

    maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    maven("https://ymind-maven.pkg.coding.net/repository/emtboot/public/")
}
