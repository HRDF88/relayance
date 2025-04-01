package com.kirabium.relayance.domain

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import com.kirabium.relayance.domain.useCase.GetCustomerByIdUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.Calendar

class GetCustomerByIdUseCaseTest {

    private lateinit var repository: CustomerRepositoryInterface
    private lateinit var getCustomerByIdUseCase: GetCustomerByIdUseCase

    @Before
    fun setUp() {

        repository = Mockito.mock(CustomerRepositoryInterface::class.java)

        getCustomerByIdUseCase = GetCustomerByIdUseCase(repository)
    }

    @Test
    fun `test execute returns customer when found`() = runBlocking {

        val customerId = 1
        val customer = CustomerDto(
            id = customerId,
            name = "Alice",
            email = "alice@example.com",
            createdAt = Calendar.getInstance().apply {
                set(2024, Calendar.MARCH, 26)
            }.time
        )
        Mockito.`when`(repository.getCustomerById(customerId)).thenReturn(customer)


        val result = getCustomerByIdUseCase.execute(customerId)


        assertEquals(customer, result)
    }
}
