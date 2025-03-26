package com.kirabium.relayance.ui.AddCustomer

import androidx.lifecycle.ViewModel
import com.kirabium.relayance.domain.useCase.InsertCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddCustomerViewModel @Inject constructor(insertCustomerUseCase: InsertCustomerUseCase) : ViewModel(){

    private val _uiState = MutableStateFlow<AddCustomerUiState>(AddCustomerUiState.Loading)
    val uiState: StateFlow<AddCustomerUiState> get() = _uiState
}