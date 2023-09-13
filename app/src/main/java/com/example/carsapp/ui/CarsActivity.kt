package com.example.carsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.carsapp.databinding.ActivityCarsBinding
import com.example.carsapp.ui.adapter.TabAdapter
import com.example.carsapp.ui.tab.TabLayout

internal class CarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()

        binding.title

        binding.carsViewPager.isVisible = true
        val bindingViewPager = binding.carsViewPager
        bindingViewPager.adapter = TabAdapter(this)

        TabLayout.viewPager = binding.carsViewPager
        binding.tabLayout.addOnTabSelectedListener(TabLayout)

        binding.emptyCarListError
        binding.internetErrorText
    }

    private fun setupBinding() {
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}