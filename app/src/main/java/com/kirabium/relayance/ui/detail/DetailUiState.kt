package com.kirabium.relayance.ui.detail

import com.kirabium.relayance.domain.model.Customer

/**
 * A sealed class representing the various UI states for displaying customer details.
 * It captures the loading, success, and error states during the customer details fetching process.
 */
sealed class DetailUiState {

    /**
     * Represents the loading state, indicating that the customer details are being loaded.
     */
    object Loading : DetailUiState()

    /**
     * Represents the success state, containing the customer data after successfully fetching the details.
     *
     * @param customers The customer data that was successfully retrieved.
     */
    data class Success(val customers: Customer) : DetailUiState()

    /**
     * Represents the error state, containing an error message if there was an issue fetching the customer details.
     *
     * @param message The error message explaining the failure.
     */
    data class Error(val message: String) : DetailUiState()
}