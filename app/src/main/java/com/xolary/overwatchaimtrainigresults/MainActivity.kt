package com.xolary.overwatchaimtrainigresults

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xolary.overwatchaimtrainigresults.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}