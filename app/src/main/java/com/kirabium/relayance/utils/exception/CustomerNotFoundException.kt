package com.kirabium.relayance.utils.exception

/**
 * Custom exception thrown when a customer is not found.
 * This exception is typically used when attempting to retrieve a customer by ID and no such customer exists.
 *
 * @param message The detail message explaining the reason for the exception.
 */
class CustomerNotFoundException(message: String) : Exception(message)