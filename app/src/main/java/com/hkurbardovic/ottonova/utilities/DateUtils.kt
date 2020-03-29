package com.hkurbardovic.ottonova.utilities

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val SERVER_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ssXXX"
    private const val READABLE_TIMESTAMP = "yyyy-MM-dd"

    fun format(serverTimestamp: String?): String? {
        try {
            if (serverTimestamp == null) return null
            val date = parseServerTimestamp(serverTimestamp) ?: return null

            val calendar = Calendar.getInstance()
            calendar.time = date

            return formatCalendar(calendar)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    private fun parseServerTimestamp(serverTimestamp: String): Date? {
        val sdf = SimpleDateFormat(SERVER_TIMESTAMP, Locale.getDefault())
        return sdf.parse(serverTimestamp)
    }

    private fun formatCalendar(calendar: Calendar): String {
        val sdf = SimpleDateFormat(READABLE_TIMESTAMP, Locale.getDefault())
        return sdf.format(calendar.time)
    }
}