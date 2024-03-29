package com.compose.aiartcreatorcompose.di

import com.compose.aiartcreatorcompose.network.ApiService
import com.compose.aiartcreatorcompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

    object NetworkModule {

        @Singleton
        @Provides
        @Named("loggingInterceptor")
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BASIC
            }
        }

        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Provides
        fun provideApiClient(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
