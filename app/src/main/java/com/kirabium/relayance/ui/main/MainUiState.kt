package com.kirabium.relayance.ui.main

import com.kirabium.relayance.domain.model.Customer

/**
 * A sealed class representing the various UI states for the main screen.
 * It captures the loading, success, and error states during the process of fetching customers.
 */
sealed class MainUiState {

    /**
     * Represents the loading state, indicating that the customer data is being loaded.
     */
    object Loading : MainUiState()

    /**
     * Represents the success state, containing a list of customers after successfully fetching the data.
     *
     * @param customers A list of customers that were successfully retrieved.
     */
    data class Success(val customers: List<Customer>) : MainUiState()

    /**
     * Represents the error state, containing an error message if there was an issue fetching the customer data.
     *
     * @param message The error message explaining the failure.
     */
    data class Error(val message: String) : MainUiState()
}
