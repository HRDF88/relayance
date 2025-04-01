package com.kirabium.relayance.di

import android.content.Context
import com.kirabium.relayance.data.dao.CustomerDao
import com.kirabium.relayance.data.database.AppDatabase
import com.kirabium.relayance.data.repository.CustomerRepository
import com.kirabium.relayance.data.repositoryInterface.CustomerRepositoryInterface
import com.kirabium.relayance.domain.useCase.GetAllCustomersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing application dependencies.
 * This module defines how the application's dependencies, such as database, DAOs, and repositories,
 * are provided to the application components.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the AppDatabase.
     *
     * @param context The application context.
     * @return The AppDatabase instance.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    /**
     * Provides the CustomerDao from the AppDatabase.
     *
     * @param database The AppDatabase instance.
     * @return The CustomerDao instance.
     */
    @Provides
    fun provideCustomerDAO(database: AppDatabase): CustomerDao {
        return database.customerDAO()
    }

    /**
     * Provides an instance of the CustomerRepositoryInterface.
     *
     * @param customerDAO The CustomerDao instance.
     * @return The CustomerRepositoryInterface instance.
     */
    @Provides
    fun provideCustomerRepository(customerDAO: CustomerDao): CustomerRepositoryInterface {
        return CustomerRepository(customerDAO)
    }

    /**
     * Provides an instance of the GetAllCustomersUseCase.
     *
     * @param customerRepository The CustomerRepositoryInterface instance.
     * @return The GetAllCustomersUseCase instance.
     */
    @Provides
    fun provideGetAllCustomersUseCase(customerRepository: CustomerRepositoryInterface): GetAllCustomersUseCase {
        return GetAllCustomersUseCase(customerRepository)
    }
}