package team.yi.rsql.tools

import java.io.Serial
import java.text.*
import java.util.*

class KmDateFormat : DateFormat() {
    override fun format(date: Date, toAppendTo: StringBuffer, fieldPosition: FieldPosition): StringBuffer {
        throw UnsupportedOperationException()
    }

    override fun parse(source: String, pos: ParsePosition): Date? {
        for (dateFormat in DATE_FORMATS) {
            try {
                return dateFormat.parse(source, pos)
            } catch (e: Exception) {
                continue
            }
        }

        return null
    }

    companion object {
        val DATE_FORMATS = KmDateUtil.DATE_FORMATS.keys.map { SimpleDateFormat(it) }

        @Serial
        private const val serialVersionUID: Long = 1
    }
}
