package com.kirabium.relayance.ui.AddCustomer

import com.kirabium.relayance.domain.model.Customer

sealed class AddCustomerUiState {
    object Loading : AddCustomerUiState()
    data class Success(val customers: Customer) : AddCustomerUiState()
    data class Error(val message: String) : AddCustomerUiState()
}