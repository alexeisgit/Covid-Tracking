package com.example.covid.ui.countrydetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.covid.CovidApp
import com.example.covid.R
import com.example.covid.datasource.DataSource
import com.example.covid.datasource.FakeDataSource
import com.example.covid.datasource.RemoteDataSource
import com.example.covid.utils.decimalFromNumber
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_country_detail.*
import kotlinx.coroutines.launch

class CountryDetailFragment : Fragment() {
    val args: CountryDetailFragmentArgs by navArgs()
    val dataSource: DataSource = CovidApp.dataSource
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* val bundle = arguments
        val id = bundle.getString("countriId")*/


        val id = args.countryId
        lifecycleScope.launch {
            val countries = dataSource.getCountriesInfo()

            val country = countries.find { it.name == id }!!
            nameTV.text = country.name
            casesTV.text = decimalFromNumber(country.cases)
            deathsTV.text = decimalFromNumber(country.deaths)
            recoveredTV.text = decimalFromNumber(country.recovered)
            Picasso.with(requireContext())
                .load(country.countryInfo.flag)
                .into(flagImageView)

        }
    }

}