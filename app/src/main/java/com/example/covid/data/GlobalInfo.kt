package com.example.covid.data

import com.example.covid.utils.decimalFromNumber

data class GlobalInfo(
    val cases: Int,
    val deaths: Int,
    val recovered: Int
){
    val casesFormatted
        get() = decimalFromNumber(cases)
    val deathsFormatted
        get() = decimalFromNumber(deaths)
    val recoveredFormatted
        get() = decimalFromNumber(recovered)
}