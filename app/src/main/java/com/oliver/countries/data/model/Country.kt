package com.oliver.countries.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val capital: String? = null,
    val code: String? = null,
    val currency: Currency? = null,
    val demonym: String? = null,
    val flag: String? = null,
    val language: Language? = null,
    val name: String? = null,
    val region: String? = null
) : Parcelable

@Parcelize
data class Currency(
    val code: String? = null,
    val name: String? = null,
    val symbol: String? = null
) : Parcelable

@Parcelize
data class Language(
    val code: String? = null,
    @field:SerializedName("iso639_2")
    val isoCode: String? = null,
    val name: String? = null,
    val nativeName: String? = null
) : Parcelable
