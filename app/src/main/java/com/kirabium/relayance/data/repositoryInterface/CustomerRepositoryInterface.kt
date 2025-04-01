package com.kirabium.relayance.data.repositoryInterface

import com.kirabium.relayance.data.dto.CustomerDto

/**
 * Interface for managing customer data operations.
 * This interface defines the methods that the repository will implement to interact with the data layer.
 */
interface CustomerRepositoryInterface {

    /**
     * Retrieves all customers from the data source.
     *
     * @return A list of all customers.
     */
    suspend fun getAllCustomers(): List<CustomerDto>

    /**
     * Inserts a new customer into the data source.
     *
     * @param customer The customer to be inserted.
     */
    suspend fun insertCustomer(customer: CustomerDto)

    /**
     * Retrieves a customer by their ID.
     *
     * @param id The unique identifier of the customer.
     * @return The customer matching the given ID.
     * @throws CustomerNotFoundException If no customer is found with the given ID.
     */
    suspend fun getCustomerById(id: Int): CustomerDto


}