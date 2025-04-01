package com.kirabium.relayance.domain.mapper

import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.domain.model.Customer

/**
 * A utility object for mapping between the domain model (Customer) and the data transfer object (CustomerDto).
 * This object provides methods for converting data between the two models used in the application.
 */
object CustomerMapper {

    /**
     * Converts a Customer domain model to a CustomerDto.
     *
     * @param customer The Customer instance to convert.
     * @return A CustomerDto corresponding to the given Customer.
     */
    fun toDTO(customer: Customer): CustomerDto {
        return CustomerDto(
            id = customer.id,
            name = customer.name,
            email = customer.email,
            createdAt = customer.createdAt
        )
    }

    /**
     * Converts a CustomerDto to a Customer domain model.
     *
     * @param dto The CustomerDto instance to convert.
     * @return A Customer corresponding to the given CustomerDto.
     */
    fun fromDTO(dto: CustomerDto): Customer {
        return Customer(id = dto.id, name = dto.name, email = dto.email, createdAt = dto.createdAt)
    }
}