package com.kirabium.relayance.data

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import com.kirabium.relayance.domain.useCase.InsertCustomerUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class InsertCustomerUseCaseTest {

    private lateinit var insertCustomerUseCase: InsertCustomerUseCase
    private val repository: CustomerRepositoryInterface = mock(CustomerRepositoryInterface::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        insertCustomerUseCase = InsertCustomerUseCase(repository)
    }

    @Test
    fun `execute should call insertCustomer on repository`() = runBlocking {

        val customer = CustomerDto(id = 1, name = "John Doe", email = "johndoe@example.com")

        insertCustomerUseCase.execute(customer)

        verify(repository).insertCustomer(customer)
    }
}
