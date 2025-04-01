package com.kirabium.relayance.di

import androidx.test.runner.AndroidJUnit4
import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.database.AppDatabase
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import com.kirabium.relayance.domain.useCase.GetAllCustomersUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AppModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: AppDatabase
    @Inject lateinit var customerDao: CustomerDao
    @Inject lateinit var customerRepository: CustomerRepositoryInterface
    @Inject lateinit var getAllCustomersUseCase: GetAllCustomersUseCase

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testDatabaseInjection() {
        assertNotNull(database)
    }

    @Test
    fun testCustomerDaoInjection() {
        assertNotNull(customerDao)
    }

    @Test
    fun testCustomerRepositoryInjection() {
        assertNotNull(customerRepository)
    }

    @Test
    fun testGetAllCustomersUseCaseInjection() {
        assertNotNull(getAllCustomersUseCase)
    }
}
