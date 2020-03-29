package com.hkurbardovic.ottonova.utilities

import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilsUnitTest {
    @Test
    fun dateUtils_format_isCorrect() {
        assertEquals("2020-03-15", DateUtils.format("2020-03-15T09:00:00+00:00"))
        assertEquals("2000-01-26", DateUtils.format("2000-01-26T09:00:00+01:00"))
    }
}
