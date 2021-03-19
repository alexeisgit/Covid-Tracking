package com.example.covid.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.covid.R
import com.example.covid.datasource.RemoteDataSource
import com.example.covid.model.CountryInfo
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_global.*
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesFragment : Fragment(), CountriesAdapter.Listener {

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
            try {
                val countries = RemoteDataSource.getCountriesInfo()
                countriesRV.adapter = CountriesAdapter(countries, this@CountriesFragment)
            } catch(e: Exception){
                Toast.makeText(requireContext(), "error while get countries info: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCountryClick(id: String) {
        val action = CountriesFragmentDirections.actionFromCountriesListToCountryDetails(id)
        findNavController().navigate(action)
    }
}