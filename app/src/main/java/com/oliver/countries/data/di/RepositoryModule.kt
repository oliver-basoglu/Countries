package com.oliver.countries.data.di

import com.oliver.countries.data.network.CountriesService
import com.oliver.countries.data.repositories.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCountriesRepository(
        countriesService: CountriesService,
        ioDispatcher: CoroutineDispatcher
    ): CountriesRepository {
        return CountriesRepository(countriesService, ioDispatcher)
    }
}
