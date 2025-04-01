package com.kirabium.relayance.ui.AddCustomer

import com.kirabium.relayance.domain.model.Customer

/**
 * A sealed class representing the various UI states for adding a customer.
 * It captures the loading, success, and error states during the customer addition process.
 */
sealed class AddCustomerUiState {

    /**
     * Represents the loading state, indicating that the customer addition process is ongoing.
     */
    object Loading : AddCustomerUiState()

    /**
     * Represents the success state, containing the customer data after a successful addition.
     *
     * @param customers The customer data that was successfully added.
     */
    data class Success(val customers: Customer) : AddCustomerUiState()

    /**
     * Represents the error state, containing an error message if the addition process fails.
     *
     * @param message The error message explaining the failure.
     */
    data class Error(val message: String) : AddCustomerUiState()
}