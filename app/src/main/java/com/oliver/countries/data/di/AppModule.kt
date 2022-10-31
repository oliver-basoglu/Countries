package com.oliver.countries.data.di

import android.app.Application
import com.oliver.countries.data.network.CountriesService
import com.oliver.countries.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(application: Application): OkHttpClient =
        OkHttpClient.Builder().cache(
            Cache(
                File(application.cacheDir, "http_cache"),
                10L * 1024L * 1024L // 10 MB
            )
        ).callTimeout(10, TimeUnit.SECONDS)
         .connectTimeout(15, TimeUnit.SECONDS)
         .build()

    @Singleton
    @Provides
    fun provideCountriesService(retrofit: Retrofit): CountriesService =
        retrofit.create(CountriesService::class.java)
}
