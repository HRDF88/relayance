package com.kirabium.relayance.viewModel

import MainDispatcherRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.useCase.GetCustomerByIdUseCase
import com.kirabium.relayance.ui.detail.DetailUiState
import com.kirabium.relayance.ui.detail.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: DetailViewModel
    private val getCustomerByIdUseCase = Mockito.mock(GetCustomerByIdUseCase::class.java)

    @Before
    fun setup() {

        viewModel = DetailViewModel(getCustomerByIdUseCase)
    }

    @Test
    fun `getCustomerDetails should update uiState with Success when customer is found`() = runTest {
        // GIVEN
        val customerId = 1
        val customerDto = CustomerDto(id = 1, name = "John Doe", email = "john@example.com") // Simulé
        val expectedCustomer = CustomerMapper.fromDTO(customerDto)

        Mockito.`when`(getCustomerByIdUseCase.execute(customerId)).thenReturn(customerDto)

        viewModel.getCustomerDetails(customerId)

        assertEquals(DetailUiState.Success(expectedCustomer), viewModel.uiState.first { it is DetailUiState.Success })
    }

    @Test
    fun `getCustomerDetails should update uiState with Error when use case throws exception`() = runTest {

        val customerId = 1
        Mockito.`when`(getCustomerByIdUseCase.execute(customerId)).thenThrow(RuntimeException("Database error"))

        viewModel.getCustomerDetails(customerId)

        val uiState = viewModel.uiState.first { it is DetailUiState.Error }
        assertTrue(uiState is DetailUiState.Error)
        assertEquals("Error loading customer details: Database error", (uiState as DetailUiState.Error).message)
    }
}
