package com.example.handyman.di

import android.content.Context
import com.example.handyman.data.local.AppDatabase
import com.example.handyman.data.local.CustomerDao
import com.example.handyman.data.remote.CustomerRemoteDataSource
import com.example.handyman.data.remote.CustomerService
import com.example.handyman.data.repository.CustomerRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://hulet.tech/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCustomerService(retrofit: Retrofit): CustomerService = retrofit.create(CustomerService::class.java)

    @Singleton
    @Provides
    fun provideCustomerRemoteDataSource(customerService: CustomerService) = CustomerRemoteDataSource(customerService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.customerDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CustomerRemoteDataSource,
                          localDataSource: CustomerDao) =
        CustomerRepository(remoteDataSource, localDataSource)
}