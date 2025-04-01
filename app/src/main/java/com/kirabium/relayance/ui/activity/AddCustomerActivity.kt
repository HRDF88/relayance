package com.kirabium.relayance.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kirabium.relayance.data.dto.CustomerDto
import com.kirabium.relayance.databinding.ActivityAddCustomerBinding
import com.kirabium.relayance.ui.AddCustomer.AddCustomerUiState
import com.kirabium.relayance.ui.AddCustomer.AddCustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCustomerBinding
    private val viewModel: AddCustomerViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbar()
        setupObservers()

    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupBinding() {
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.saveFab.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()


            if (isValidEmail(email)) {
                val customer = CustomerDto(id = 0, name = name, email = email)
                viewModel.addCustomer(customer)
            } else {
                showSnackbar("Format d'email invalide")
            }
        }


    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is AddCustomerUiState.Loading -> {

                    }

                    is AddCustomerUiState.Success -> {
                        showSnackbar("Client ajouté avec succès")
                        setResult(Activity.RESULT_OK)
                        finish()
                    }

                    is AddCustomerUiState.Error -> {
                        showSnackbar(state.message)
                    }
                }
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}