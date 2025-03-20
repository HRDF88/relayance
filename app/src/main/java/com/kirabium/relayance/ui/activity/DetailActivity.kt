package com.kirabium.relayance.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import com.kirabium.relayance.ui.composable.DetailScreen
import com.kirabium.relayance.ui.detail.DetailUiState
import com.kirabium.relayance.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CUSTOMER_ID = "customer_id"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }


    private fun setupUI() {

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val customerId = intent.getIntExtra(EXTRA_CUSTOMER_ID, -1)
        if (customerId != -1) {


            setContent {
                val uiState by detailViewModel.uiState.observeAsState(DetailUiState.Loading)

                LaunchedEffect(customerId) {
                    detailViewModel.getCustomerDetails(customerId)
                }

                when (uiState) {
                    is DetailUiState.Loading -> {
                    }

                    is DetailUiState.Success -> {
                        val customer = (uiState as DetailUiState.Success).customers
                        DetailScreen(customer = customer) {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    }

                    is DetailUiState.Error -> {
                        val errorMessage = (uiState as DetailUiState.Error).message
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}


