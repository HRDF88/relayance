package com.kirabium.relayance

import com.kirabium.relayance.data.DummyData.generateDate
import com.kirabium.relayance.domain.model.Customer
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CustomerTest {

    @Test
    fun `isNewCustomer should return true for customers registered within the last 3 months`() {
        val recentDate = generateDate(2)
        val customer = Customer(1, "New User", "new@example.com", recentDate)
        assertTrue(customer.isNewCustomer())
    }

    @Test
    fun `isNewCustomer should return false for customers registered more than 3 months ago`() {
        val oldDate = generateDate(4)
        val customer = Customer(2, "Old User", "old@example.com", oldDate)
        assertFalse(customer.isNewCustomer())
    }
}