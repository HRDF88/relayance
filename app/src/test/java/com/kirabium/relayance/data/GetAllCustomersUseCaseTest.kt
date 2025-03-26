package com.kirabium.relayance.data

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import com.kirabium.relayance.domain.useCase.GetAllCustomersUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.Calendar

class GetAllCustomersUseCaseTest {

    private lateinit var repository: CustomerRepositoryInterface
    private lateinit var getAllCustomersUseCase: GetAllCustomersUseCase

    @Before
    fun setUp() {

        repository = Mockito.mock(CustomerRepositoryInterface::class.java)

        getAllCustomersUseCase = GetAllCustomersUseCase(repository)
    }

    @Test
    fun `test execute calls repository and returns customer list`() = runBlocking {

        val customerList = listOf(
            CustomerDto(1, "Alice", "alice@example.com", Calendar.getInstance().apply {
                set(2024, Calendar.MARCH, 26)
            }.time),
            CustomerDto(2, "Bob", "bob@example.com", Calendar.getInstance().apply {
                set(2024, Calendar.SEPTEMBER, 26)
            }.time)
        )

        Mockito.`when`(repository.getAllCustomers()).thenReturn(customerList)

        val result = getAllCustomersUseCase.execute()

        assertEquals(customerList, result)
    }
}