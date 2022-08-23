package com.melike.mobile_summer_intern.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.melike.mobile_summer_intern.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}