package com.kirabium.relayance.domain.useCase

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import javax.inject.Inject

class GetCustomerByIdUseCase @Inject constructor(
    private val customerRepository: CustomerRepositoryInterface
) {
    suspend fun execute(id: Int): CustomerDto {
        return customerRepository.getCustomerById(id)
    }
}