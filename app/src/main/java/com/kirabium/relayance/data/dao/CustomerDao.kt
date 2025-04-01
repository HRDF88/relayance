package com.kirabium.relayance.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kirabium.relayance.data.dto.CustomerDto

/**
 * Data Access Object (DAO) for accessing customer data in the database.
 */
@Dao
interface CustomerDao {

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers stored in the database.
     */
    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers(): List<CustomerDto>

    /**
     * Inserts a customer into the database. If the customer already exists, it will be replaced.
     *
     * @param customer The customer to insert into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: CustomerDto)

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The customer matching the given ID, or null if not found.
     */
    @Query("SELECT * FROM customers WHERE id = :customerId LIMIT 1")
    suspend fun getCustomerById(customerId: Int): CustomerDto?
}