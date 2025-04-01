package com.kirabium.relayance.ui.AddCustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.useCase.InsertCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the UI state related to adding a customer.
 * This ViewModel interacts with the InsertCustomerUseCase to insert a new customer and updates the UI state.
 *
 * @param insertCustomerUseCase The use case for inserting a new customer.
 */
@HiltViewModel
class AddCustomerViewModel @Inject constructor(private val insertCustomerUseCase: InsertCustomerUseCase) :
    ViewModel() {

    /**
     * A MutableStateFlow representing the current state of the UI.
     * It starts in the Loading state and changes based on the success or failure of adding a customer.
     */
    private val _uiState = MutableStateFlow<AddCustomerUiState>(AddCustomerUiState.Loading)

    /**
     * A StateFlow representing the public UI state.
     * Observers can collect this flow to react to UI state changes.
     */
    val uiState: StateFlow<AddCustomerUiState> get() = _uiState

    /**
     * Adds a new customer by invoking the use case and updating the UI state.
     * This method sets the state to loading, attempts to add the customer, and updates the state based on the outcome.
     *
     * @param customer The customer data to be added.
     */
    fun addCustomer(customer: CustomerDto) {
        viewModelScope.launch {
            try {
                _uiState.value = AddCustomerUiState.Loading
                insertCustomerUseCase.execute(customer)
                val customerResult = CustomerMapper.fromDTO(customer)
                _uiState.value = AddCustomerUiState.Success(customerResult)
            } catch (e: Exception) {
                _uiState.value = AddCustomerUiState.Error("Erreur lors de l'ajout du client")
            }
        }
    }
}