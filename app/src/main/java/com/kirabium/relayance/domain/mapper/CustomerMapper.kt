package com.kirabium.relayance.domain.mapper

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.domain.model.Customer
import java.util.Date

object CustomerMapper {
    fun toDTO(customer: Customer): CustomerDto {
        return CustomerDto(id = customer.id, name = customer.name, email = customer.email, createdAt = customer.createdAt)
    }

    fun fromDTO(dto: CustomerDto): Customer {
        return Customer(id = dto.id, name = dto.name, email = dto.email, createdAt = dto.createdAt)
    }
}