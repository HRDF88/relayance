package com.kirabium.relayance.domain.useCase

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import javax.inject.Inject

/**
 * Use case for retrieving a customer by their unique ID.
 * This class interacts with the CustomerRepositoryInterface to fetch the customer data.
 *
 * @param customerRepository The repository interface used to access customer data.
 */
class GetCustomerByIdUseCase @Inject constructor(
    private val customerRepository: CustomerRepositoryInterface
) {

    /**
     * Executes the use case to retrieve a customer by their ID.
     *
     * @param id The unique identifier of the customer to fetch.
     * @return The customer corresponding to the given ID.
     * @throws CustomerNotFoundException If no customer is found with the given ID.
     */
    suspend fun execute(id: Int): CustomerDto {
        return customerRepository.getCustomerById(id)
    }
}