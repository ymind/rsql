package team.yi.tools.ktables

class Table(
    val header: Header,
    val rows: List<Row>,
    val footer: Footer? = null,
) {
    private val renderer = TableRenderer(this)

    fun build(): String {
        return buildString {
            renderer.render(this)
        }
    }

    private fun maxLen(columnIndex: Int): Int {
        return rows.maxOf { row ->
            maxOf(
                row.cells[columnIndex].width,
                header.cells[columnIndex].width,
            )
        }
    }

    internal val columnWidthMap: Map<Int, Int> by lazy {
        List(header.cells.size) { index ->
            Pair(index, maxLen(index))
        }.toMap()
    }

    internal val footerWidth: Int by lazy {
        maxOf(
            footer?.width ?: 0,
            List(header.cells.size) { index ->
                columnWidthMap.getValue(index) + 3
            }.sum() - 3,
        )
    }
}

fun calcTextWidth(value: String): Int {
    return value.map {
        if (it.toString().toByteArray().size > 1) {
            2
        } else {
            1
        }
    }.sum()
}
