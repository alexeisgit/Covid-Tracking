package com.example.covid.datasource

import com.example.covid.model.CountryInfo
import com.example.covid.model.GlobalInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidService {
    @GET("v3/covid-19/all")
    fun getGlobalInfo(): GlobalInfo

    @GET("v3/covid-19/countries")
    fun getCountriesInfo(): List<CountryInfo>
}


val retrofit = Retrofit.Builder()
        .baseUrl("https://corona.lmao.ninja/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

val service = retrofit.create(CovidService.class)