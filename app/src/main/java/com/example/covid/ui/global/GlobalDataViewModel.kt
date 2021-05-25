package com.example.covid.ui.global

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid.CovidApp
import com.example.covid.data.GlobalInfo
import kotlinx.coroutines.launch
import java.lang.Exception


class GlobalDataViewModel: ViewModel(){
    val isLoadingLiveData = MutableLiveData<Boolean>(false)
    val globalInfoLiveData = MutableLiveData<GlobalInfo>(null)
    val errorLiveData = MutableLiveData<String>()

    fun refreshData(){
        viewModelScope.launch{
            isLoadingLiveData.value = true
            try {
                val globalInfo = CovidApp.dataSource.getGlobalInfo() //2 sec
                globalInfoLiveData.value = globalInfo
            }catch(e: Exception){
                errorLiveData.value = "error while get global info: ${e.message}"
            }
            isLoadingLiveData.value = false
        }
    }
}



/*
class GlobalDataViewModel : ViewModel(){
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val globalInfoLiveData = MutableLiveData<GlobalInfo>()

    fun refreshData(){
        GlobalScope.launch {
            isLoadingLiveData.value = true
            //view.showProgressBar()

            val globalInfo = RemoteDataSource.getGlobalInfo() //2 sec
            globalInfoLiveData.value = globalInfo
            //view.showGlobalInfo(globalInfo)

            //view.hideProgressBar()
            isLoadingLiveData.value = false
        }
    }
}*/
