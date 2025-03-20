package com.kirabium.relayance.ui.detail

import com.kirabium.relayance.domain.model.Customer

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val customers: Customer) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}