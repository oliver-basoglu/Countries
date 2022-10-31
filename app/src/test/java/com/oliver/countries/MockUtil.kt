
package com.oliver.countries

import com.oliver.countries.data.model.Country


object MockUtil {

  fun mockCountryList() = listOf(mockCountry())

  private fun mockCountry() = Country(
    capital = "Washington D.C.",
    name = "United States",
    code = "US"
  )
}
