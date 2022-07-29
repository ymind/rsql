package team.yi.tools.ktables

open class Cell(
    override val text: String,
    override val align: TableAlignment? = null,
) : ICell {
    override val width: Int by lazy { calcTextWidth(text) }
    override val height: Int by lazy { maxOf(text.split("\n").size, 1) }

    override fun hashCode(): Int {
        return text.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val o = other as? Cell ?: return false

        return text == o.text
    }
}

data class Header(
    val cells: List<Cell>,
    override val align: TableAlignment? = TableAlignment.LEFT,
) : Cell(cells.joinToString { it.text }, align) {
    override val isEmpty: Boolean
        get() = cells.all { it.isEmpty }
}

data class Row(
    val cells: List<Cell>,
    override val align: TableAlignment? = TableAlignment.LEFT,
) : Cell(cells.joinToString { it.text }, align) {
    override val isEmpty: Boolean
        get() = cells.all { it.isEmpty }
}

data class Footer(
    override val text: String,
    override val align: TableAlignment? = TableAlignment.LEFT,
) : Cell(text, align)

interface ICell {
    val text: String
    val align: TableAlignment?
    val width: Int
    val height: Int

    val isEmpty: Boolean
        get() = text.isBlank()
}
