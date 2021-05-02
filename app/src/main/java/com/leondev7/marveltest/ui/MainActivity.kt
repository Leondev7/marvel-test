package com.leondev7.marveltest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leondev7.marveltest.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single Activity EntryPoint for the application
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
