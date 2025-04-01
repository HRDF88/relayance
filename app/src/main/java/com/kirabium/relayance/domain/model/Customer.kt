package com.kirabium.relayance.domain.model

import java.util.Calendar
import java.util.Date

/**
 * Data class representing a customer in the domain model.
 *
 * @property id The unique identifier for the customer.
 * @property name The name of the customer.
 * @property email The email address of the customer.
 * @property createdAt The date when the customer was created.
 */
data class Customer(val id: Int, val name: String, val email: String, val createdAt: Date) {

    /**
     * Determines if the customer is considered new, based on their creation date.
     * A customer is considered new if their account was created within the last 3 months.
     *
     * @return `true` if the customer was created within the last 3 months, `false` otherwise.
     */
    fun isNewCustomer(): Boolean {
        val today = Calendar.getInstance()
        val createdAtCalendar = Calendar.getInstance().apply {
            time = createdAt
        }
        today.add(Calendar.MONTH, -3)
        return !createdAtCalendar.before(today)
    }
}
