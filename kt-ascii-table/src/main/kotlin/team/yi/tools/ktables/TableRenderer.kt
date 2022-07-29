package team.yi.tools.ktables

class TableRenderer(private val table: Table) {
    fun render(builder: StringBuilder) {
        renderHeaders(builder)
        renderBody(builder)
        renderFooter(builder)
    }

    private fun renderHeaders(builder: StringBuilder) {
        renderHeader(builder, table.header)
    }

    private fun renderHeader(builder: StringBuilder, header: Header) {
        header.cells.forEachIndexed { index, _ ->
            val width = cellWidth(index)

            when (index) {
                0 -> builder.append("--")
                else -> builder.append("---")
            }

            builder.append("-".repeat(width))

            if (index == table.header.cells.size - 1) builder.append("--")
        }

        builder.appendLine()

        header.cells.forEachIndexed { index, cell ->
            val width = cellWidth(index)

            renderCell(builder, cell, index, width)
        }

        builder.appendLine()

        header.cells.forEachIndexed { index, _ ->
            val width = cellWidth(index)

            when (index) {
                0 -> builder.append("--")
                else -> builder.append("-|-")
            }

            builder.append("-".repeat(width))

            if (index == table.header.cells.size - 1) builder.append("--")
        }
    }

    private fun renderBody(builder: StringBuilder) {
        for (row in table.rows) {
            renderRow(builder, row)
        }
    }

    private fun renderRow(builder: StringBuilder, row: Row) {
        builder.appendLine()

        row.cells.forEachIndexed { index, cell ->
            val width = cellWidth(index)

            renderCell(builder, cell, index, width)
        }
    }

    private fun renderFooter(builder: StringBuilder) {
        builder.appendLine()

        table.header.cells.forEachIndexed { index, _ ->
            val width = cellWidth(index)

            when (index) {
                0 -> builder.append("--")
                else -> builder.append("---")
            }

            builder.append("-".repeat(width))

            if (index == table.header.cells.size - 1) builder.append("--")
        }

        if (table.footer == null || table.footer.isEmpty) return

        builder.appendLine()

        renderCell(builder, table.footer, -1, table.footerWidth)

        builder.appendLine()

        table.header.cells.forEachIndexed { index, _ ->
            val width = cellWidth(index)

            when (index) {
                0 -> builder.append("--")
                else -> builder.append("---")
            }

            builder.append("-".repeat(width))

            if (index == table.header.cells.size - 1) builder.append("--")
        }
    }

    private fun renderCell(builder: StringBuilder, cell: Cell, index: Int, width: Int) {
        when (index) {
            0, -1 -> builder.append("| ")
            else -> builder.append(" | ")
        }

        val content = buildString {
            append(cell.text)
        }

        val cellAlign = cell.align ?: table.header.cells[maxOf(index, 0)].align ?: table.header.align
        val spaceWidth = maxOf(width - cell.width, 0)

        when (cellAlign) {
            TableAlignment.CENTER -> {
                val leftWidth = spaceWidth / 2
                val rightWidth = spaceWidth - leftWidth

                builder.append(" ".repeat(leftWidth))
                builder.append(content)
                builder.append(" ".repeat(rightWidth))
            }

            TableAlignment.RIGHT -> {
                builder.append(" ".repeat(spaceWidth))
                builder.append(content)
            }

            else -> {
                builder.append(content)
                builder.append(" ".repeat(spaceWidth))
            }
        }

        if (index == table.header.cells.size - 1 || index == -1) builder.append(" |")
    }

    private fun cellWidth(index: Int): Int {
        return table.columnWidthMap.getValue(index).let { cellWidth ->
            if (index == table.header.cells.size - 1) {
                val footerWidth = table.footerWidth - (table.header.cells.size - 1) * 3
                val fullWidth = table.columnWidthMap.map { it.value }.sum()
                val diff = footerWidth - fullWidth

                if (diff > 0) diff + cellWidth else cellWidth
            } else {
                cellWidth
            }
        }
    }
}
