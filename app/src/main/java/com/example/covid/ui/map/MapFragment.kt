package com.example.covid.ui.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.covid.CovidApp
import com.example.covid.R
import com.example.covid.data.CountryInfo
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class MapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */


        lifecycleScope.launch {
            val countries = CovidApp.dataSource.getCountriesInfo()

/*            val groups = createGroups(countries)
            createMarkers(groups, googleMap)*/

            val maxDeathsCountry = countries.maxBy { it.deathsPerOneMillion }
            val maxDeaths = maxDeathsCountry?.deathsPerOneMillion ?: 0.0
            val minDeathsCountry = countries.minBy { it.deathsPerOneMillion }
            val minDeaths = minDeathsCountry?.deathsPerOneMillion ?: 0.0
            for (country in countries) {
                val group = getGroup(minDeaths, maxDeaths, country)
                makeMarker(group, googleMap, country)
            }
        }
    }

    private fun makeMarker(group: Int, googleMap: GoogleMap, country: CountryInfo) {
        val coord = LatLng(country.countryInfo.lat, country.countryInfo.long)
        val markerIcon = when (group) {
            0 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            1 -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            else -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        }
        googleMap.addMarker(MarkerOptions().position(coord).title(country.name).icon(markerIcon))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coord))
    }


    private fun getGroup(minDeaths: Double, maxDeaths: Double, country: CountryInfo): Int {
        val groupNumber = when {
            country.deathsPerOneMillion <= maxDeaths / 3 -> 0
            country.deathsPerOneMillion > maxDeaths / 3 && country.deathsPerOneMillion <= 2 * maxDeaths / 3 -> 1
            else -> 2
        }
        return groupNumber
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}


/*
   private fun createMarkers(groups: List<List<CountryInfo>>, googleMap: GoogleMap) {
    for (country in groups[0]) {
        val coord = LatLng(country.countryInfo.lat, country.countryInfo.long)
        val markerIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
        googleMap.addMarker(MarkerOptions().position(coord).title(country.name).icon(markerIcon))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coord))
    }

    for (country in groups[1]) {
        val coord = LatLng(country.countryInfo.lat, country.countryInfo.long)
        val markerIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        googleMap.addMarker(MarkerOptions().position(coord).title(country.name).icon(markerIcon))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coord))
    }

    for (country in groups[2]) {
        val coord = LatLng(country.countryInfo.lat, country.countryInfo.long)
        val markerIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        googleMap.addMarker(MarkerOptions().position(coord).title(country.name).icon(markerIcon))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coord))
    }
}

private fun createGroups(countries: List<CountryInfo>):List<List<CountryInfo>>  {
    val group0 = mutableListOf<CountryInfo>()
    val group1 = mutableListOf<CountryInfo>()
    val group2 = mutableListOf<CountryInfo>()
    val maxDeathsCountry = countries.maxBy { it.deathsPerOneMillion }
    val maxDeaths = maxDeathsCountry?.deathsPerOneMillion ?: 0.0
    val minDeathsCountry = countries.minBy { it.deathsPerOneMillion }
    val minDeaths = minDeathsCountry?.deathsPerOneMillion ?: 0.0

    for (country in countries) {
        when {
            country.deathsPerOneMillion >= minDeaths && country.deathsPerOneMillion <= maxDeaths / 3 -> group0.add(country)
            country.deathsPerOneMillion > maxDeaths / 3 && country.deathsPerOneMillion <= 2 * maxDeaths / 3 -> group1.add(country)
            else -> group2.add(country)
        }
    }
    return listOf(group0, group1, group2)
}*/
