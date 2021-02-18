package com.example.covid.ui.global

import com.example.covid.datasource.RemoteDataSource
import com.example.covid.model.GlobalInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface GlobalInfoView{
    fun showProgressBar()
    fun showGlobalInfo(globalInfo: GlobalInfo)
    fun hideProgressBar()
}

class GlobalDataPresenter(val view: GlobalInfoView) {
    fun refreshData(){
        GlobalScope.launch {
            view.showProgressBar()
            val globalInfo = RemoteDataSource.getGlobalInfo() //2 sec
            view.showGlobalInfo(globalInfo)
            view.hideProgressBar()
        }
    }
}