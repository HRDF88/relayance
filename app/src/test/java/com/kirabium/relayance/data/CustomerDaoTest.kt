package com.kirabium.relayance.data


import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.dto.CustomerDto
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.Calendar

class CustomerDaoTest {

    private lateinit var customerDao: CustomerDao

    @Before
    fun setUp() {

        customerDao = mock()
    }

    @Test
    fun `test getAllCustomers returns a list of customers`() = runBlocking {

        val customerList = listOf(
            CustomerDto(1, "Alice", "alice@example.com", Calendar.getInstance().apply {
                set(2024, Calendar.MARCH, 26)
            }.time),
            CustomerDto(2, "Bob", "bob@example.com", Calendar.getInstance().apply {
                set(2024, Calendar.SEPTEMBER, 26)
            }.time)
        )

        Mockito.`when`(customerDao.getAllCustomers()).thenReturn(customerList)

        val result = customerDao.getAllCustomers()

        assertEquals(customerList, result)
    }

    @Test
    fun `test insertCustomer inserts a customer`() = runBlocking {

        val customer = CustomerDto(1, "Alice", "alice@example.com", Calendar.getInstance().apply {
            set(2024, Calendar.MARCH, 26)
        }.time)

        customerDao.insertCustomer(customer)

        Mockito.verify(customerDao).insertCustomer(customer)
    }

    @Test
    fun `test getCustomerById returns a customer when it exists`() = runBlocking {

        val customerId = 1
        val customer = CustomerDto(
            id = customerId,
            name = "Alice",
            email = "alice@example.com",
            createdAt = Calendar.getInstance().apply {
                set(2024, Calendar.MARCH, 26)
            }.time
        )

        Mockito.`when`(customerDao.getCustomerById(customerId)).thenReturn(customer)

        val result = customerDao.getCustomerById(customerId)

        assertEquals(customer, result)
    }
}