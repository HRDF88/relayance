package com.kirabium.relayance.data

import com.kirabium.relayance.data.database.Converters
import org.junit.Assert.*
import org.junit.Test
import java.util.Date

class ConvertersTest {

    private val converters = Converters()

    @Test
    fun `fromTimestamp should convert Long to Date`() {
        val timestamp = 1_700_000_000_000L
        val expectedDate = Date(timestamp)

        val result = converters.fromTimestamp(timestamp)

        assertNotNull(result)
        assertEquals(expectedDate, result)
    }

    @Test
    fun `fromTimestamp should return null when input is null`() {
        val result = converters.fromTimestamp(null)
        assertNull(result)
    }

    @Test
    fun `dateToTimestamp should convert Date to Long`() {
        val date = Date(1_700_000_000_000L)

        val result = converters.dateToTimestamp(date)

        assertNotNull(result)
        assertEquals(1_700_000_000_000L, result)
    }

    @Test
    fun `dateToTimestamp should return null when input is null`() {
        val result = converters.dateToTimestamp(null)
        assertNull(result)
    }
}
