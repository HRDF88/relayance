package com.kirabium.relayance.domain.useCase

import android.util.Log
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import javax.inject.Inject

class GetAllCustomersUseCase @Inject constructor(private val repository: CustomerRepositoryInterface) {

    suspend fun execute(): List<CustomerDto> {
        Log.d("GetAllCustomersUseCase", "Executing GetAllCustomersUseCase...")
        val customers = repository.getAllCustomers()
        Log.d("GetAllCustomersUseCase", "Fetched customers: $customers")
        return customers
    }
}