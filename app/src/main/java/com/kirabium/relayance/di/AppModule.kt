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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideCustomerDAO(database: AppDatabase): CustomerDao {
        return database.customerDAO()
    }

    @Provides
    fun provideCustomerRepository(customerDAO: CustomerDao): CustomerRepositoryInterface {
        return CustomerRepository(customerDAO)
    }

    @Provides
    fun provideGetAllCustomersUseCase(customerRepository: CustomerRepositoryInterface): GetAllCustomersUseCase {
        return GetAllCustomersUseCase(customerRepository)
    }
}