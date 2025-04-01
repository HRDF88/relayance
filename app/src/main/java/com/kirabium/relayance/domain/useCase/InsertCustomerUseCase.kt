package com.kirabium.relayance.domain.useCase

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import javax.inject.Inject

/**
 * Use case for inserting a new customer into the repository.
 * This class interacts with the CustomerRepositoryInterface to insert customer data.
 *
 * @param repository The repository interface used to perform customer data operations.
 */
class InsertCustomerUseCase @Inject constructor(private val repository: CustomerRepositoryInterface) {

    /**
     * Executes the use case to insert a new customer into the repository.
     *
     * @param customer The customer data to be inserted.
     */
    suspend fun execute(customer: CustomerDto) {
        repository.insertCustomer(customer)
    }
}