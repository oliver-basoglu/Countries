package com.oliver.countries.data.repositories

import com.oliver.countries.API_FETCHING_ERROR
import com.oliver.countries.data.model.CountriesResponseResult
import com.oliver.countries.data.network.CountriesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Error
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val countriesService: CountriesService,
    private val ioDispatcher: CoroutineDispatcher
) {
    
    fun fetchCountriesData() = flow {
        emit(CountriesResponseResult.IsLoading)
        val response = countriesService.getCountriesData()
        if (response != null) {
            val result = CountriesResponseResult.Success(response)
            emit(result)
        } else {
            emit(CountriesResponseResult.Failure(Error(API_FETCHING_ERROR)))
        }
    }.flowOn(ioDispatcher)
}
