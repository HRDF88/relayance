package com.kirabium.relayance.viewModel

import MainDispatcherRule
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.domain.useCase.InsertCustomerUseCase
import com.kirabium.relayance.ui.AddCustomer.AddCustomerViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class AddCustomerViewModelTest {

    private lateinit var viewModel: AddCustomerViewModel
    private val insertCustomerUseCase: InsertCustomerUseCase = mock()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = AddCustomerViewModel(insertCustomerUseCase)
    }

    @Test
    fun `addCustomer should call insertCustomerUseCase`() = runTest {
        val customer = CustomerDto(1, "Jane Doe", "jane.doe@example.com")

        viewModel.addCustomer(customer)

        verify(insertCustomerUseCase).execute(customer)
    }
}

