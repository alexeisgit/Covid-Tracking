package com.example.covid.ui.countries

import android.app.AlarmManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.covid.R
import com.example.covid.datasource.RemoteDataSource
import com.example.covid.model.CountryInfo
import com.example.covid.ui.countrydetails.CountryDetailFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesFragment : Fragment(), CountrieAdapterListener {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_countries, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshCountries()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.refreshButtonItem -> refreshCountries()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun refreshCountries() {
        lifecycleScope.launch {
            try {

    /*                val listener = object : CountrieAdapterListener{
                        override fun onCountryClick(id: String) {
                            val action = CountriesFragmentDirections.actionFromCountriesListToCountryDetails(id)
                            findNavController().navigate(action)
                        }
                    }*/
                progressBar.visibility = View.VISIBLE
                val countries = RemoteDataSource.getCountriesInfo()
                countriesRV.adapter = CountriesAdapter(countries, this@CountriesFragment)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "error while get countries info: ${e.message}", Toast.LENGTH_LONG).show()
            }
            progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onCountryClick(id: String) {
        /*//val bundle = Bundle()
        //bundle.putString("countriId", id)
        val detailFragment = CountryDetailFragment(id)
        //detailFragment.arguments = bundle
        fragmentManager.cahngeFragment(detailFragment)*/

        val action = CountriesFragmentDirections.actionFromCountriesListToCountryDetails(id)
        findNavController().navigate(action)
    }
}

