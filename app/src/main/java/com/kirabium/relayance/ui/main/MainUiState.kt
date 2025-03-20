package com.kirabium.relayance.ui.main

import com.kirabium.relayance.domain.model.Customer

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val customers: List<Customer>) : MainUiState()
    data class Error(val message: String) : MainUiState()
}
