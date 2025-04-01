package com.kirabium.relayance.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.useCase.GetAllCustomersUseCase
import com.kirabium.relayance.ui.main.MainUiState
import com.kirabium.relayance.ui.main.MainViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.util.Date

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private val getAllCustomer=  Mockito.mock(GetAllCustomersUseCase::class.java)

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(getAllCustomer)
    }

    @Test
    fun `loadCustomers should update uiState with Success when customers are fetched successfully`() = runBlockingTest {

        val customerDtoList = listOf(
            CustomerDto(1, "Alice", "alice@example.com", Date()),
            CustomerDto(2, "Bob", "bob@example.com", Date())
        )
        val expectedCustomers = customerDtoList.map { CustomerMapper.fromDTO(it) }

        Mockito.`when`(getAllCustomer.execute()).thenReturn(customerDtoList)

        viewModel.loadCustomers()

        assertEquals(MainUiState.Success(expectedCustomers), viewModel.uiState.first { it is MainUiState.Success })
    }

    @Test
    fun `loadCustomers should update uiState with Error when an exception is thrown`() = runBlockingTest {

        val errorMessage = "Network error"
        Mockito.`when`(getAllCustomer.execute()).thenThrow(RuntimeException(errorMessage))

        viewModel.loadCustomers()

        val uiState = viewModel.uiState.first { it is MainUiState.Error }
        assertTrue(uiState is MainUiState.Error)
        assertEquals(errorMessage, (uiState as MainUiState.Error).message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
