package com.example.covid.model

import com.example.covid.utils.decimalFromNumber
import com.google.gson.annotations.SerializedName
import java.util.*

data class CountryInfo(
    @SerializedName(value = "country")val name: String,
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    val deathsPerOneMillion: Double,
    val countryInfo: CountryExtraInfo
)


data class CountryExtraInfo(
    val lat: Double,
    val long: Double,
    val flag: String
)