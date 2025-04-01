package com.kirabium.relayance.data.repository

import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import com.kirabium.relayance.utils.exception.CustomerNotFoundException
import javax.inject.Inject

/**
 * Repository class for managing customer data.
 * This class acts as an intermediary between the data source (DAO) and the business logic.
 *
 * @property customerDao The Data Access Object (DAO) used to interact with the database.
 */
class CustomerRepository @Inject constructor(private val customerDao: CustomerDao) :
    CustomerRepositoryInterface {

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers.
     */
    override suspend fun getAllCustomers(): List<CustomerDto> {
        return customerDao.getAllCustomers()
    }

    /**
     * Inserts a new customer into the database.
     *
     * @param customer The customer to be inserted.
     */
    override suspend fun insertCustomer(customer: CustomerDto) {
        customerDao.insertCustomer(customer)
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id The unique identifier of the customer.
     * @return The customer matching the given ID.
     * @throws CustomerNotFoundException If no customer is found with the given ID.
     */
    override suspend fun getCustomerById(id: Int): CustomerDto {
        return customerDao.getCustomerById(id)
            ?: throw CustomerNotFoundException("Customer not found with id: $id")
    }
}