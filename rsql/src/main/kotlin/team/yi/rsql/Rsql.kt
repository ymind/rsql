package team.yi.rsql

import com.mybatisflex.core.query.QueryWrapper
import org.jooq.DSLContext
import team.yi.rsql.jooq.*
import team.yi.rsql.mybatisflex.*
import team.yi.rsql.zeko.*

object Rsql {
    fun zeko(
        /**
         * When this option is true, some string variables in the SQL statement will be replaced by "?",
         * and the relevant parameter values will be saved to `RsqlOutput<Q, R>.queryParts`,
         * you need to process them yourself when executing the SQL statement
         */
        useRawValue: Boolean = true,
    ): ZekoRsqlProvider = zeko(ZekoRsqlConfig(useRawValue))

    fun zeko(config: ZekoRsqlConfig): ZekoRsqlProvider = ZekoRsqlProvider(config)

    fun jooq(create: DSLContext): JooqRsqlProvider = jooq(create, JooqRsqlConfig())
    fun jooq(create: DSLContext, config: JooqRsqlConfig): JooqRsqlProvider = JooqRsqlProvider(create, config)

    fun myBatisFlex(queryWrapper: QueryWrapper): MybatisFlexRsqlProvider = myBatisFlex(queryWrapper, MybatisFlexRsqlConfig())
    fun myBatisFlex(queryWrapper: QueryWrapper, config: MybatisFlexRsqlConfig): MybatisFlexRsqlProvider = MybatisFlexRsqlProvider(queryWrapper, config)
}
