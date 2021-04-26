package com.example.covid.ui.countries

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.covid.R
import com.example.covid.datasource.RemoteDataSource
import com.example.covid.model.CountryInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesFragment : Fragment(), CountrieAdapterListener {
    var countries: List<CountryInfo> = emptyList()
    var filterText: String = ""
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
        sortSwitch.setOnCheckedChangeListener { _, isChecked ->
            filterAndSortAndDisplay()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                /*val filteredCountries = if (newText != null)
                    countries.filter { it.name.startsWith(newText, true) }
                else
                    countries.filter { it.name.startsWith("", true) }*/
/*                val filteredCountries = countries.filter { it.name.startsWith(newText ?: "", true) }
                val adapter = CountriesAdapter(filteredCountries, this@CountriesFragment)
                countriesRV.adapter = adapter*/
                filterText = newText ?: ""
                filterAndSortAndDisplay()
                return true
            }

        })
        refreshCountries()
    }

    private fun filterAndSortAndDisplay() {
        val filteredCountries = countries.filter { it.name.startsWith(filterText, true) }
        val filteredAndSortedCountries = if (sortSwitch.isChecked)
            filteredCountries.sortedByDescending { it.cases }
        else
            filteredCountries.sortedBy { it.name }

        val adapter = CountriesAdapter(filteredAndSortedCountries, this@CountriesFragment)
        countriesRV.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.refresh_menu, menu)
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
                progressBar.visibility = View.VISIBLE
                countries = RemoteDataSource.getCountriesInfo()
                filterAndSortAndDisplay()
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

