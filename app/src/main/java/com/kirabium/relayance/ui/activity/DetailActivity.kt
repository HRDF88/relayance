package com.kirabium.relayance.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.kirabium.relayance.ui.composable.DetailScreen
import com.kirabium.relayance.ui.detail.DetailUiState
import com.kirabium.relayance.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CUSTOMER_ID = "customer_id"
        fun create(targetContext: Context?, nIdCustomer: Int): Intent {
            return Intent(targetContext, DetailActivity::class.java).putExtra(
                EXTRA_CUSTOMER_ID,
                nIdCustomer
            )
        }
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
                val uiState by detailViewModel.uiState.collectAsState(DetailUiState.Loading)

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



