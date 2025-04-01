package com.kirabium.relayance.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.useCase.GetCustomerByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the UI state related to displaying customer details.
 * This ViewModel interacts with the GetCustomerByIdUseCase to fetch the customer details and updates the UI state accordingly.
 *
 * @param getCustomerByIdUseCase The use case for retrieving customer details by ID.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(private val getCustomerByIdUseCase: GetCustomerByIdUseCase) :
    ViewModel() {

    /**
     * A MutableStateFlow representing the current state of the UI for customer details.
     * It starts in the Loading state and changes based on the success or failure of fetching customer details.
     */
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)

    /**
     * A StateFlow representing the public UI state for customer details.
     * Observers can collect this flow to react to UI state changes.
     */
    val uiState: StateFlow<DetailUiState> get() = _uiState

    /**
     * Fetches the customer details by invoking the use case and updating the UI state based on the outcome.
     * This method sets the state to loading, attempts to fetch the customer details, and updates the state.
     *
     * @param id The unique identifier of the customer whose details are to be fetched.
     */
    suspend fun getCustomerDetails(id: Int) {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            try {
                val customer = getCustomerByIdUseCase.execute(id)
                val customerFromDto = CustomerMapper.fromDTO(customer)
                _uiState.value = DetailUiState.Success(customerFromDto)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error("Error loading customer details: ${e.message}")
            }
        }
    }
}