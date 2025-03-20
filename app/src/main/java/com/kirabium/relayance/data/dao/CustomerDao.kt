package com.kirabium.relayance.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kirabium.relayance.data.dto.CustomerDto

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers(): List<CustomerDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: CustomerDto)

    @Query("SELECT * FROM customers WHERE id = :customerId LIMIT 1")
    suspend fun getCustomerById(customerId: Int): CustomerDto?
}