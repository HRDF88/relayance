package com.kirabium.relayance.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.useCase.GetAllCustomersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the UI state related to the main screen, specifically loading and displaying a list of customers.
 * This ViewModel interacts with the GetAllCustomersUseCase to fetch the customer list and updates the UI state.
 *
 * @param getAllCustomer The use case for retrieving all customers.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val getAllCustomer: GetAllCustomersUseCase) :
    ViewModel() {

    /**
     * A MutableStateFlow representing the current state of the UI for the main screen.
     * It starts in the Loading state and changes based on the success or failure of loading customers.
     */
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)

    /**
     * A StateFlow representing the public UI state for the main screen.
     * Observers can collect this flow to react to UI state changes.
     */
    val uiState: StateFlow<MainUiState> get() = _uiState

    /**
     * Loads the list of customers by invoking the use case and updating the UI state based on the result.
     * This method sets the state to loading, fetches the customer data, and updates the state accordingly.
     */
    fun loadCustomers() {
        _uiState.value = MainUiState.Loading
        viewModelScope.launch {
            try {
                val customersDto = getAllCustomer.execute()

                val customers = customersDto.map { customerDto ->
                    CustomerMapper.fromDTO(customerDto)

                }

                _uiState.value = MainUiState.Success(customers.toList())
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error(e.message ?: "Une erreur est survenue")
            }
        }
    }
}
