package com.kirabium.relayance.data

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.Date
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CustomerRepositoryInterfaceTest {

    @Mock
    lateinit var mockCustomerRepository: CustomerRepositoryInterface

    private val sampleCustomer = CustomerDto(1, "Alice", "alice@example.com", Date())

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAllCustomers() = runBlocking {

        val customersList = listOf(sampleCustomer)
        Mockito.`when`(mockCustomerRepository.getAllCustomers()).thenReturn(customersList)

        val result = mockCustomerRepository.getAllCustomers()

        assertNotNull(result)
        assertEquals(customersList, result)
    }

    @Test
    fun testInsertCustomer() = runBlocking {
        mockCustomerRepository.insertCustomer(sampleCustomer)

        Mockito.verify(mockCustomerRepository).insertCustomer(sampleCustomer)
    }

    @Test
    fun testGetCustomerById() = runBlocking {

        Mockito.`when`(mockCustomerRepository.getCustomerById(1)).thenReturn(sampleCustomer)

        val result = mockCustomerRepository.getCustomerById(1)

        assertNotNull(result)
        assertEquals(sampleCustomer, result)
    }
}

