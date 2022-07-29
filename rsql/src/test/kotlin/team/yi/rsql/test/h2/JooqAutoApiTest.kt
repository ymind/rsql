package team.yi.rsql.test.h2

import com.fasterxml.jackson.databind.JsonNode
import com.zaxxer.hikari.HikariDataSource
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dromara.hutool.core.util.RandomUtil
import org.jooq.*
import org.jooq.impl.DSL
import org.junit.jupiter.api.Test
import team.yi.jacksync.JacksonObjectMapperWrapper
import team.yi.jacksync.merge.ObjectMergeProcessor

class JooqAutoApiTest : H2BaseTest() {
    private val kLogger = KotlinLogging.logger {}

    private fun getDslContext(): DSLContext {
        val dataSource = HikariDataSource(hikariConfig)

        return DSL.using(dataSource, SQLDialect.H2)
    }

    @Test
    fun post() {
        // insert into "T_DEPARTMENT"("NAME", "LOCATION") values ('tech', 'Guangzhou');
        val data = mapOf<String, Any?>(
            "NAME" to "tech_" + RandomUtil.randomString(8),
            "LOCATION" to "Guangzhou_" + RandomUtil.randomString(8),
        )
        val table = DSL.table("T_DEPARTMENT")

        val dslContext = getDslContext()
        val inserted = dslContext.insertInto(table)
            .set(data)
            .newRecord()
            .set(data)
            .newRecord()
            .set(data)
            .returningResult(DSL.field("ID"), DSL.field("NAME"))
            .apply {
                kLogger.info { "this.sql: ${this.sql}" }
            }
            .fetchMaps()

        kLogger.info { "inserted: $inserted" }

        dslContext.selectFrom(table).fetchMaps()
    }

    @Test
    fun patch() {
        val table = DSL.table("T_DEPARTMENT")

        val dslContext = getDslContext()

        val existingItem = dslContext.selectFrom(table)
            .where(DSL.field("NAME").eq("tech"))
            .fetchOneMap() ?: return

        val objectMapperWrapper = JacksonObjectMapperWrapper()
        val data = objectMapperWrapper.valueToTree<JsonNode>(
            mapOf<String, Any?>(
                "NAME" to "tech_${RandomUtil.randomString(8)}",
                "LOCATION" to "Guangzhou_${RandomUtil.randomString(8)}",
            )
        )

        val mergeProcessor = ObjectMergeProcessor(objectMapperWrapper)
        val merged = mergeProcessor.merge(existingItem, data)

        kLogger.info { "existingItem: $existingItem" }
        kLogger.info { "      merged: $merged" }

        dslContext.update(table)
            .set(merged)
            .where(DSL.field("NAME").eq("tech"))
            .execute()

        dslContext.selectFrom(table).fetchMaps()
    }
}
