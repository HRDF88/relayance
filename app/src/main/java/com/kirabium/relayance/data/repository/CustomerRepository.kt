package com.kirabium.relayance.data.repository

import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import com.kirabium.relayance.utils.exception.CustomerNotFoundException
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val customerDao: CustomerDao) :
    CustomerRepositoryInterface {
    override suspend fun getAllCustomers(): List<CustomerDto> {
        return customerDao.getAllCustomers()
    }

    override suspend fun insertCustomer(customer: CustomerDto) {
        customerDao.insertCustomer(customer)
    }

    override suspend fun getCustomerById(id : Int) : CustomerDto{
        return customerDao.getCustomerById(id) ?: throw CustomerNotFoundException("Customer not found with id: $id")
    }
}