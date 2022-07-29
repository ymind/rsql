package team.yi.rsql.test.mysql

import com.fasterxml.jackson.databind.JsonNode
import com.zaxxer.hikari.HikariDataSource
import io.github.oshai.kotlinlogging.KotlinLogging
import org.dromara.hutool.core.util.RandomUtil
import org.jooq.*
import org.jooq.conf.Settings
import org.jooq.impl.*
import org.junit.jupiter.api.Test
import team.yi.jacksync.JacksonObjectMapperWrapper
import team.yi.jacksync.merge.ObjectMergeProcessor

class JooqAutoApiTest : BaseMySqlTest() {
    private val kLogger = KotlinLogging.logger {}

    private fun getDslContext(): DSLContext {
        val dataSource = HikariDataSource(hikariConfig)

        val settings = Settings().apply {
            this
                .withExecuteLogging(false)
                .withRenderFormatted(true)
                .withRenderCatalog(true)
                .withRenderSchema(true)
                .withReturnAllOnUpdatableRecord(true)
                .withReturnDefaultOnUpdatableRecord(true)
                .withReturnComputedOnUpdatableRecord(true)
                .withReturnIdentityOnUpdatableRecord(true)
        }

        val configuration = DefaultConfiguration().apply {
            this.set(dataSource)
            this.set(settings)
            this.set(SQLDialect.MYSQL)
            // this.set(DefaultVisitListenerProvider(BindValueAbbreviator()))
            // this.set(DefaultExecuteListenerProvider(KmDefaultExecuteListener()))
        }

        return DSL.using(configuration)
    }

    @Test
    fun post() {
        // insert into "T_DEPARTMENT"("NAME", "LOCATION") values ('tech', 'Guangzhou');
        val data = mapOf<String, Any?>(
            "id" to RandomUtil.randomInt(),
            "name" to "tech_" + RandomUtil.randomString(8),
            "location" to "Guangzhou_" + RandomUtil.randomString(8),
        )
        val table = DSL.table("t_department")

        // val sqlDataType = BuiltInDataType.getDataType(null, Int::class.java)

        val dslContext = getDslContext()
        val inserted = dslContext.insertInto(table)
            .set(data)
            // .newRecord()
            // .set(data)
            // .newRecord()
            // .set(data)
            // .returningResult(
            //     // DSL.field(it, sqlDataType.identity(true))
            //     DSL.field("id", sqlDataType.identity(true)),
            // )
            .returning()
            .fetchMaps()

        kLogger.info { "inserted: $inserted" }

        dslContext.selectFrom(table).fetchMaps()
    }

    @Test
    fun patch() {
        val table = DSL.table("t_department")

        val dslContext = getDslContext()

        val existingItem = dslContext.selectFrom(table)
            .where(DSL.field("name").eq("tech"))
            .fetchOneMap() ?: return

        val objectMapperWrapper = JacksonObjectMapperWrapper()
        val data = objectMapperWrapper.valueToTree<JsonNode>(
            mapOf<String, Any?>(
                "name" to "tech_${RandomUtil.randomString(8)}",
                "location" to "Guangzhou_${RandomUtil.randomString(8)}",
            )
        )

        val mergeProcessor = ObjectMergeProcessor(objectMapperWrapper)
        val merged = mergeProcessor.merge(existingItem, data)

        kLogger.info { "existingItem: $existingItem" }
        kLogger.info { "      merged: $merged" }

        dslContext.update(table)
            .set(merged)
            .where(DSL.field("name").eq("tech"))
            .execute()

        dslContext.selectFrom(table).fetchMaps()
    }
}
