package com.kirabium.relayance.domain.useCase

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import javax.inject.Inject

class InsertCustomerUseCase@Inject constructor(private val repository: CustomerRepositoryInterface) {
    suspend fun execute(customer: CustomerDto) {
        repository.insertCustomer(customer)
    }
}