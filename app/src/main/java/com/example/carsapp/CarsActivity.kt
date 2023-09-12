package com.example.carsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carsapp.databinding.ActivityCarsBinding

class CarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}