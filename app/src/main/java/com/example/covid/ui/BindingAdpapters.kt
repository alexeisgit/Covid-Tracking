package com.example.covid.ui

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["app:showIfTrue"], requireAll = false)
fun showIfTrue(view: View, isLoading: Boolean){
    view.visibility = if(isLoading == true)
        View.VISIBLE
    else
        View.INVISIBLE
}