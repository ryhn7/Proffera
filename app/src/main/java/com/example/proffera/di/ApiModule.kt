package com.example.proffera.di

import android.content.Context
import com.example.proffera.data.remote.retrofit.ApiConfig
import com.example.proffera.data.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideApiService(@ApplicationContext context: Context): ApiService =
        ApiConfig.getApiService(context)
}