package com.kirabium.relayance

import com.kirabium.relayance.data.DummyData.generateDate
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DummyDataTest {

    private lateinit var dateFormat: SimpleDateFormat

    @Before
    fun setup() {
        dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    }

    @Test
    fun `generateDate should return a date X months back`() {
        val monthsBack = 6
        val generatedDate = generateDate(monthsBack)

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -monthsBack)
        val expectedDate = calendar.time

        val expectedFormatted = dateFormat.format(expectedDate)
        val generatedFormatted = dateFormat.format(generatedDate)

        assertEquals(expectedFormatted, generatedFormatted)
    }
}