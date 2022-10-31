package com.oliver.countries.data.network

import com.oliver.countries.data.model.Country
import retrofit2.http.GET

interface CountriesService {

    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountriesData(): List<Country>?
}
