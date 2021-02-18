package com.example.covid.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.covid.R
import com.example.covid.datasource.RemoteDataSource
import com.example.covid.model.CountryInfo
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_global.*
import kotlinx.coroutines.launch

class CountriesFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_countries, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val countries: List<CountryInfo> = RemoteDataSource.getCountriesInfo()

            countriesRV.adapter = CountriesAdapter(countries)
        }

    }
}