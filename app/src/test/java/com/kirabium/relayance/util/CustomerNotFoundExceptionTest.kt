package com.kirabium.relayance.util

import com.kirabium.relayance.utils.exception.CustomerNotFoundException
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows

class CustomerNotFoundExceptionTest {

    @Test
    fun `test exception message`() {
        val exception = assertThrows(CustomerNotFoundException::class.java) {
            throw CustomerNotFoundException("Customer not found")
        }

        assertEquals("Customer not found", exception.message)
    }
}