package com.oliver.countries.data.model

import java.lang.Error

sealed class CountriesResponseResult {
    class Success(val countries: List<Country>) : CountriesResponseResult()
    class Failure(val error: Error) : CountriesResponseResult()
    object IsLoading : CountriesResponseResult()
}
