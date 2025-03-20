package com.kirabium.relayance.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.domain.mapper.CustomerMapper
import com.kirabium.relayance.domain.useCase.GetCustomerByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getCustomerByIdUseCase: GetCustomerByIdUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<DetailUiState>()
    val uiState: LiveData<DetailUiState> get() = _uiState

    suspend fun getCustomerDetails(id: Int) {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            try {
                val customer = getCustomerByIdUseCase.execute(id)
                val customerFromDto = CustomerMapper.fromDTO(customer)
                _uiState.value = DetailUiState.Success(customerFromDto)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error("Error loading customer details: ${e.message}")
            }
        }
    }
}