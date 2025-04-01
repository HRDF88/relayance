package com.kirabium.relayance.domain.useCase

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import javax.inject.Inject

/**
 * Use case for retrieving all customers.
 * This class interacts with the CustomerRepositoryInterface to fetch customer data.
 *
 * @param repository The repository interface used to access customer data.
 */
class GetAllCustomersUseCase @Inject constructor(private val repository: CustomerRepositoryInterface) {

    /**
     * Executes the use case to retrieve all customers from the repository.
     *
     * @return A list of all customers.
     * @throws Exception If an error occurs while fetching the customers.
     */
    suspend fun execute(): List<CustomerDto> {
        val customers = repository.getAllCustomers()
        return customers
    }
}