package team.yi.rsql.test.h2

import org.jooq.*

class KmDefaultExecuteListener : ExecuteListener {
    override fun executeStart(ctx: ExecuteContext) {
        // val query = ctx.query() as SelectQuery<*>
        // query.addConditions()

        println("xx.")
    }
}
