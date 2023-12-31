package com.apple.itunesapi.presentation.di

import com.apple.itunesapi.BuildConfig
import com.apple.itunesapi.data.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Module for getting retrofit and API services instance
@Module
@InstallIn(SingletonComponent::class)

class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiServices(retrofit: Retrofit): APIService{
        return retrofit.create(APIService::class.java)
    }
}