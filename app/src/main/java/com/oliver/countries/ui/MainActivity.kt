package com.oliver.countries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oliver.countries.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
