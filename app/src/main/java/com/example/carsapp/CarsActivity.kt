package com.example.carsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carsapp.databinding.ActivityMainBinding

class CarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}