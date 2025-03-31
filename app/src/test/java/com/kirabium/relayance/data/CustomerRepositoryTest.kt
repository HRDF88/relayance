package com.kirabium.relayance.data

import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repository.CustomerRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.Calendar

class CustomerRepositoryTest {

    private lateinit var customerDao: CustomerDao
    private lateinit var customerRepository: CustomerRepository

    @Before
    fun setUp() {

        customerDao = Mockito.mock(CustomerDao::class.java)

        customerRepository = CustomerRepository(customerDao)
    }

    @Test
    fun `test getAllCustomers calls the dao and returns customer list`() = runBlocking {

        val customerList = listOf(
            CustomerDto(1, "Alice", "alice@example.com", Calendar.getInstance().apply {
                set(2024, Calendar.MARCH, 26)
            }.time),
            CustomerDto(2, "Bob", "bob@example.com", Calendar.getInstance().apply {
                set(2024, Calendar.SEPTEMBER, 26)
            }.time)
        )

        Mockito.`when`(customerDao.getAllCustomers()).thenReturn(customerList)

        val result = customerRepository.getAllCustomers()

        assertEquals(customerList, result)
    }

    @Test
    fun `test insertCustomer calls the dao to insert a customer`() = runBlocking {

        val customer = CustomerDto(1, "Alice", "alice@example.com", Calendar.getInstance().apply {
            set(2024, Calendar.MARCH, 26)
        }.time)


        customerRepository.insertCustomer(customer)


        Mockito.verify(customerDao).insertCustomer(customer)
    }

    @Test
    fun `test getCustomerById returns customer when found`() = runBlocking {

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


        val result = customerRepository.getCustomerById(customerId)

        assertEquals(customer, result)
    }
}