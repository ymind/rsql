import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import team.yi.gradle.plugin.FileSet

object Constants {
    const val GIT_URL = "github.com"
    const val GIT_PROJECT_URL = "ymind/rsql"

    const val PROJECT_VERSION = "0.92.6-SNAPSHOT"
}

plugins {
    java
    `maven-publish`
    signing

    kotlin("jvm")

    // https://plugins.gradle.org/plugin/team.yi.semantic-gitlog
    id("team.yi.semantic-gitlog") version "0.6.12"

    // https://plugins.gradle.org/plugin/io.gitlab.arturbosch.detekt
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
}

group = "team.yi.rsql"
version = Constants.PROJECT_VERSION
description = "Integration RSQL query language and Querydsl framework."

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    version = rootProject.version

    if (project.name == "rsql-bom" || project.name == "rsql-deps") return@subprojects

    apply(plugin = "java-library")
    apply(plugin = "kotlin")

    dependencies {
        api(platform(project(":rsql-deps")))

        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

        // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
        testImplementation("ch.qos.logback:logback-classic")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        withJavadocJar()
        withSourcesJar()

        val mainSourceSet = sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME)

        registerFeature("provider") {
            usingSourceSet(mainSourceSet)
        }
    }

    kotlin {
        compilerOptions {
            apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
            jvmTarget = JvmTarget.JVM_17

            freeCompilerArgs.addAll(
                "-Xjsr305=strict",
                "-Xjvm-default=all",
                "-opt-in=kotlin.RequiresOptIn",
            )
        }
    }

    tasks {
        compileJava { options.encoding = "UTF-8" }
        compileTestJava { options.encoding = "UTF-8" }
        javadoc { options.encoding = "UTF-8" }

        jar { enabled = true }
        test { useJUnitPlatform() }
    }
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        buildUponDefaultConfig = true
        allRules = true
        autoCorrect = true
        config.from(files("$rootDir/config/detekt/detekt.yml"))
        baseline = file("$rootDir/config/detekt/baseline.xml")
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${detekt.toolVersion}")
    }

    if (project == rootProject) return@allprojects

    rootProject.evaluationDependsOnChildren()

    publishing {
        publications {
            register("mavenJava", MavenPublication::class) {
                if (project.name == "rsql-bom" || project.name == "rsql-deps") {
                    from(components["javaPlatform"])
                } else {
                    from(components["java"])

                    versionMapping {
                        usage("java-api") {
                            fromResolutionOf("runtimeClasspath")
                        }
                        usage("java-runtime") {
                            fromResolutionResult()
                        }
                    }
                }

                pom {
                    group = rootProject.group
                    name.set(project.name)
                    description.set(project.description)
                    url.set("https://${Constants.GIT_URL}/${Constants.GIT_PROJECT_URL}")
                    inceptionYear.set("2020")

                    scm {
                        url.set("https://${Constants.GIT_URL}/${Constants.GIT_PROJECT_URL}")
                        connection.set("scm:git:git@${Constants.GIT_URL}:${Constants.GIT_PROJECT_URL}.git")
                        developerConnection.set("scm:git:git@${Constants.GIT_URL}:${Constants.GIT_PROJECT_URL}.git")
                    }

                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                            distribution.set("repo")
                        }
                    }

                    organization {
                        name.set("Yi.Team")
                        url.set("https://yi.team/")
                    }

                    developers {
                        developer {
                            name.set("ymind")
                            email.set("ymind@yi.team")
                            url.set("https://yi.team/")
                            organization.set("Yi.Team")
                            organizationUrl.set("https://yi.team/")
                        }
                    }

                    issueManagement {
                        system.set("GitHub")
                        url.set("https://${Constants.GIT_URL}/${Constants.GIT_PROJECT_URL}/issues")
                    }

                    ciManagement {
                        system.set("GitHub")
                        url.set("https://${Constants.GIT_URL}/${Constants.GIT_PROJECT_URL}/actions")
                    }
                }
            }
        }

        repositories {
            maven {
                val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                val snapshotsRepoUrl = uri("https://ymind-maven.pkg.coding.net/repository/kmoo/public/")

                url = if (version.toString().endsWith("SNAPSHOT", true)) snapshotsRepoUrl else releasesRepoUrl

                credentials {
                    username = System.getenv("OSSRH_USERNAME") ?: "${properties["CODING_KMOO_USERNAME"]}"
                    password = System.getenv("OSSRH_TOKEN") ?: "${properties["CODING_KMOO_TOKEN"]}"
                }
            }
        }
    }

    signing {
        val secretKeyRingFile = System.getenv("OSSRH_GPG_SECRET_KEY") ?: "${properties["OSSRH_GPG_SECRET_KEY"]}"

        extra.set("signing.keyId", System.getenv("OSSRH_GPG_SECRET_ID") ?: "${properties["OSSRH_GPG_SECRET_ID"]}")
        extra.set("signing.secretKeyRingFile", "${rootDir.absolutePath}/$secretKeyRingFile")
        extra.set("signing.password", System.getenv("OSSRH_GPG_SECRET_PASSWORD") ?: "${properties["OSSRH_GPG_SECRET_PASSWORD"]}")

        sign(publishing.publications.getByName("mavenJava"))
    }
}

tasks {
    val logFileSets = setOf(
        FileSet(
            file("${rootProject.rootDir}/config/gitlog/CHANGELOG.md.mustache"),
            file("${rootProject.rootDir}/CHANGELOG.md")
        ),
        FileSet(
            file("${rootProject.rootDir}/config/gitlog/CHANGELOG_zh-cn.md.mustache"),
            file("${rootProject.rootDir}/CHANGELOG_zh-cn.md")
        )
    )
    val logLocaleProfiles = mapOf(
        "zh-cn" to file("${rootProject.rootDir}/config/gitlog/commit-locales_zh-cn.md")
    )

    changelog {
        group = "gitlog"

        toRef = "main"
        preRelease = "SNAPSHOT"

        issueUrlTemplate = "https://${Constants.GIT_URL}/${Constants.GIT_PROJECT_URL}/issues/:issueId"
        commitUrlTemplate = "https://${Constants.GIT_URL}/${Constants.GIT_PROJECT_URL}/commit/:commitId"
        mentionUrlTemplate = "https://${Constants.GIT_URL}/:username"
        // jsonFile = file("${rootProject.rootDir}/CHANGELOG.json")
        fileSets = logFileSets
        commitLocales = logLocaleProfiles

        outputs.upToDateWhen { false }
    }

    derive {
        group = "gitlog"

        toRef = "main"
        derivedVersionMark = "NEXT_VERSION:=="
        preRelease = "SNAPSHOT"
        commitLocales = logLocaleProfiles

        outputs.upToDateWhen { false }
    }
}

tasks.register("bumpVersion") {
    group = "gitlog"

    dependsOn(":changelog")

    doLast {
        var newVersion = rootProject.findProperty("newVersion") as? String

        if (newVersion.isNullOrEmpty()) {
            // ^## ([\d\.]+(-SNAPSHOT)?) \(.+\)$
            val changelogContents = file("${rootProject.rootDir}/CHANGELOG.md").readText()
            val versionRegex = Regex("^## ([\\d\\.]+(-SNAPSHOT)?) \\(.+\\)\$", setOf(RegexOption.MULTILINE))
            val changelogVersion = versionRegex.find(changelogContents)?.groupValues?.get(1)

            changelogVersion?.let { newVersion = it }

            logger.warn("changelogVersion: {}", changelogVersion)
            logger.warn("newVersion: {}", newVersion)
        }

        newVersion?.let {
            logger.info("Set Project to new Version $it")

            val contents = buildFile.readText()
                .replaceFirst("const val projectVersion = \"$version\"", "const val projectVersion = \"$it\"")

            buildFile.writeText(contents)
        }
    }
}
