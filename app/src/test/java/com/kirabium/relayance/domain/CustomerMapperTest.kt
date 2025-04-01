package com.kirabium.relayance.domain

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.model.Customer
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class CustomerMapperTest {

    @Test
    fun `toDTO should map Customer to CustomerDto correctly`() {

        val customer = Customer(1, "Alice", "alice@example.com", Date())

        val dto = CustomerMapper.toDTO(customer)

        assertEquals(customer.id, dto.id)
        assertEquals(customer.name, dto.name)
        assertEquals(customer.email, dto.email)
        assertEquals(customer.createdAt, dto.createdAt)
    }

    @Test
    fun `fromDTO should map CustomerDto to Customer correctly`() {

        val dto = CustomerDto(1, "Bob", "bob@example.com", Date())

        val customer = CustomerMapper.fromDTO(dto)

        assertEquals(dto.id, customer.id)
        assertEquals(dto.name, customer.name)
        assertEquals(dto.email, customer.email)
        assertEquals(dto.createdAt, customer.createdAt)
    }
}
