package com.kirabium.relayance.extension

import com.kirabium.relayance.extension.DateExt.Companion.toHumanDate
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Calendar
import java.util.Date

class DateExtTest {

    @Test
    fun `test date formatting with normal date`() {
        val calendar = Calendar.getInstance()
        calendar.set(2025, Calendar.JANUARY, 1)
        val date = calendar.time
        assertEquals("01/01/2025", date.toHumanDate())
    }

    @Test
    fun `test date formatting with another date`() {
        val calendar = Calendar.getInstance()
        calendar.set(1999, Calendar.DECEMBER, 31)
        val date = calendar.time
        assertEquals("31/12/1999", date.toHumanDate())
    }

    @Test
    fun `test date formatting with Unix epoch`() {
        val date = Date(0)
        assertEquals("01/01/1970", date.toHumanDate())
    }

    @Test
    fun `test date formatting with leap year`() {
        val calendar = Calendar.getInstance()
        calendar.set(2024, Calendar.FEBRUARY, 29)
        val date = calendar.time
        assertEquals("29/02/2024", date.toHumanDate())
    }

    @Test
    fun `test date formatting with different locales`() {
        val calendar = Calendar.getInstance()
        calendar.set(2023, Calendar.AUGUST, 15)
        val date = calendar.time
        assertEquals("15/08/2023", date.toHumanDate())
    }
}
