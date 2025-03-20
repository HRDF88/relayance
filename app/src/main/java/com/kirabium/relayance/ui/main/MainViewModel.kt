package com.kirabium.relayance.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.useCase.GetAllCustomersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAllCustomer: GetAllCustomersUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<MainUiState>()
    val uiState: LiveData<MainUiState> get() = _uiState

    fun loadCustomers() {
        _uiState.value = MainUiState.Loading
        viewModelScope.launch {
            try {
                Log.d("MainViewModel", "Loading customers...")
                val customersDto = getAllCustomer.execute()
                Log.d("MainViewModel", "Customers fetched: $customersDto")

                val customers = customersDto.map { customerDto ->
                    CustomerMapper.fromDTO(customerDto)
                }
                Log.d("MainViewModel", "Mapped customers: $customers")

                _uiState.value = MainUiState.Success(customers)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading customers: ${e.message}")
                _uiState.value = MainUiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}
