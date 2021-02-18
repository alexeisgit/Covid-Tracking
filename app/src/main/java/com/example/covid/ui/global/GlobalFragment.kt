package com.example.covid.ui.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.covid.R
import com.example.covid.datasource.RemoteDataSource
import kotlinx.android.synthetic.main.fragment_global.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GlobalFragment : Fragment() {
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
        lifecycleScope.launch {
            val globalInfo = RemoteDataSource.getGlobalInfo()
            casesTextView.text = globalInfo.cases.toString()
            deathsTextView.text = globalInfo.deaths.toString()
            recoveredTextView.text = globalInfo.recovered.toString()
        }
    }
}