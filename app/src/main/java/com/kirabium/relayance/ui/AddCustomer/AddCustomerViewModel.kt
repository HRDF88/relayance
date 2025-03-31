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

@HiltViewModel
class AddCustomerViewModel @Inject constructor(private val insertCustomerUseCase: InsertCustomerUseCase) : ViewModel(){

    private val _uiState = MutableStateFlow<AddCustomerUiState>(AddCustomerUiState.Loading)
    val uiState: StateFlow<AddCustomerUiState> get() = _uiState

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