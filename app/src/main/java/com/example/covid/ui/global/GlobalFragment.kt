package com.example.covid.ui.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.covid.R
import com.example.covid.databinding.FragmentGlobalBinding
import com.example.covid.model.GlobalInfo
import com.example.covid.ui.countries.CountriesAdapter
import kotlinx.android.synthetic.main.fragment_global.*

class GlobalFragment : Fragment() {
    //val presenter = GlobalDataPresenter(this)
//    val viewModel = GlobalDataViewModel()
    val viewModel: GlobalDataViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_global, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        refreshData()

/*        refreshButton.setOnClickListener {
            refreshData()
        }*/
        val binding = FragmentGlobalBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
/*        viewModel.isLoadingLiveData.observeForever{
            if (it == true)
                showProgressBar()
            else
                hideProgressBar()
        }*/
        /*viewModel.globalInfoLiveData.observeForever{
            showGlobalInfo(it)
        }*/
    }

    private fun refreshData() {
        viewModel.refreshData()
    }

/*    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }*/

/*    fun showGlobalInfo(globalInfo: GlobalInfo){
        casesTextView.text = "globalInfo.cases.toString()
        deathsTextView.text = globalInfo.deaths.toString()
        recoveredTextView.text = globalInfo.recovered.toString()
    }*/
}