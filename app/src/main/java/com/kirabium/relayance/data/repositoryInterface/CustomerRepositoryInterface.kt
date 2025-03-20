package com.kirabium.relayance.data.repositoryInterface

import com.kirabium.relayance.data.dto.CustomerDto

interface CustomerRepositoryInterface {

    suspend fun getAllCustomers(): List<CustomerDto>

    suspend fun insertCustomer(customer: CustomerDto)

    suspend fun getCustomerById(id : Int) : CustomerDto


}