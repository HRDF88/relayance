package com.kirabium.relayance.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.databinding.ActivityMainBinding
import com.kirabium.relayance.ui.adapter.CustomerAdapter
import com.kirabium.relayance.ui.main.MainUiState
import com.kirabium.relayance.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_CUSTOMER_REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var customerAdapter: CustomerAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupCustomerRecyclerView()
        setupFab()
        setupViewModel()
    }

    private fun setupFab() {
        binding.addCustomerFab.setOnClickListener {
            val intent = Intent(this, AddCustomerActivity::class.java)
            startActivityForResult(intent, ADD_CUSTOMER_REQUEST_CODE)
        }
    }

    private fun setupCustomerRecyclerView() {
        binding.customerRecyclerView.layoutManager = LinearLayoutManager(this)
        customerAdapter = CustomerAdapter(DummyData.customers) { customer ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_CUSTOMER_ID, customer.id)
            }
            startActivity(intent)
        }
        binding.customerRecyclerView.adapter = customerAdapter
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        lifecycleScope.launch {
            mainViewModel.uiState.collect { uiState ->
                Log.d("MainActivity", "UI state updated: $uiState")
                when (uiState) {
                    is MainUiState.Loading -> {
                        binding.customerRecyclerView.visibility = View.GONE
                        Log.d("MainActivity", "Loading state: Showing loading spinner...")
                    }

                    is MainUiState.Success -> {
                        binding.customerRecyclerView.visibility = View.VISIBLE
                        Log.d("MainActivity", "Success state: Showing customers list...")

                        customerAdapter.updateCustomers(uiState.customers)
                    }

                    is MainUiState.Error -> {
                        binding.customerRecyclerView.visibility = View.GONE
                        Log.e("MainActivity", "Error state: ${uiState.message}")
                        Toast.makeText(
                            this@MainActivity,
                            "Error: ${uiState.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        mainViewModel.loadCustomers()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            mainViewModel.loadCustomers()
        }
    }
}
