package com.example.covid

import android.app.Application
import android.graphics.Bitmap
import com.example.covid.data.CountryInfo
import com.example.covid.data.GlobalInfo
import com.example.covid.datasource.DataSource
import com.example.covid.datasource.FakeDataSource
import com.example.covid.datasource.RemoteDataSource
import com.example.covid.ui.countries.CountriesFragment
import com.example.covid.ui.countrydetails.CountryDetailFragment
import com.squareup.picasso.Picasso

class CovidApp: Application() { // ServiceLocator
    companion object {
        val dataSource: DataSource = RemoteDataSource
    }
}