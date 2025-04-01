package com.kirabium.relayance.ui

import com.kirabium.relayance.domain.model.Customer
import com.kirabium.relayance.ui.adapter.CustomerAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.Date

class CustomerAdapterTest {
    private lateinit var adapter: CustomerAdapter

    @Before
    fun setup() {
        val customers = listOf(
            Customer(1, "Alice", "alice@example.com",Date()),
            Customer(2, "Bob", "bob@example.com", Date())
        )
        val mockOnClick: (Customer) -> Unit = mock()
        adapter = CustomerAdapter(customers, mockOnClick)
    }

    @Test
    fun getItemCount_returnsCorrectSize() {
        assertEquals(2, adapter.itemCount)
    }
}
