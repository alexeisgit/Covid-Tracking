package com.example.covid.datasource

import com.example.covid.model.CountryInfo
import com.example.covid.model.GlobalInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.lang.Exception

object RemoteDataSource {
    private interface CovidService {
        @GET("v3/covid-19/all")
        suspend fun getGlobalInfo(): GlobalInfo

        @GET("v3/covid-19/countries")
        suspend fun getCountriesInfo(): List<CountryInfo>
    }


    private val retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val covidAPI = retrofit.create(CovidService::class.java)

    suspend fun getGlobalInfo(): GlobalInfo{
        return covidAPI.getGlobalInfo()
    }
    suspend fun getCountriesInfo(): List<CountryInfo>{
        val countriesList = covidAPI.getCountriesInfo()
        return countriesList
    }
}