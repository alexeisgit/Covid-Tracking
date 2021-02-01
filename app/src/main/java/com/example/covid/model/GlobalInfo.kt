package com.example.covid.model

import com.google.gson.annotations.SerializedName

data class GlobalInfo(
    val cases: Int,
    val deaths: Int,
    val recovered: Int
)