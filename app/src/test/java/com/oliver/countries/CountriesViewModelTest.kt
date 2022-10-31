package com.oliver.countries

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.oliver.countries.data.network.CountriesService
import com.oliver.countries.data.repositories.CountriesRepository
import com.nhaarman.mockitokotlin2.verify
import com.oliver.countries.ui.countries.CountriesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.oliver.countries.data.model.CountriesResponseResult
import kotlinx.coroutines.runBlocking
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration


class CountriesViewModelTest {

    private lateinit var countriesViewModel: CountriesViewModel
    private lateinit var countriesRepository: CountriesRepository
    private val countriesService: CountriesService = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        countriesRepository = CountriesRepository(countriesService, Dispatchers.IO)
        countriesViewModel = CountriesViewModel(countriesRepository)
    }


    @OptIn(ExperimentalTime::class)
    @Test
    fun fetchPokemonListTest() = runBlocking {
        val mockData = MockUtil.mockCountryList()
        whenever(countriesService.getCountriesData()).thenReturn(mockData)

        val fetchedDataFlow = countriesRepository.fetchCountriesData().test(1.toDuration(DurationUnit.SECONDS)) {
            val countriesResponseResultLoading = awaitItem() as CountriesResponseResult.IsLoading
            val countriesResponseResult = awaitItem() as CountriesResponseResult.Success
            assertEquals(countriesResponseResult.countries[0].capital, "Washington D.C.")
            assertEquals(countriesResponseResult.countries[0].name, "United States")
            assertEquals(countriesResponseResult.countries[0].code, "US")
            awaitComplete()
        }

        countriesViewModel.fetchCountriesData()

        verify(countriesService, atLeastOnce()).getCountriesData()

        fetchedDataFlow.apply {
            // runBlocking should return Unit
        }
    }
}