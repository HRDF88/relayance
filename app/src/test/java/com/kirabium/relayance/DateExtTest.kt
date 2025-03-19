package com.kirabium.relayance

import com.kirabium.relayance.extension.DateExt.Companion.toHumanDate
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Calendar

class DateExtTest {

    @Test
    fun `toHumanDate should format date correctly`() {
        val date = Calendar.getInstance().apply {
            set(2023, Calendar.MARCH, 15)
        }.time

        val expected = "15/03/2023"
        assertEquals(expected, date.toHumanDate())
    }
}