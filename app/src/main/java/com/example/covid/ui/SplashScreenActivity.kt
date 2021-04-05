package com.example.covid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.covid.MainActivity
import com.example.covid.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val context = this
        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(context, MainActivity::class.java))
            finish()
        }
    }
}