package com.example.carsapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.carsapp.CarsListState
import com.example.carsapp.CarsListViewModel
import com.example.carsapp.R
import com.example.carsapp.common.CheckNetworkConnection
import com.example.carsapp.databinding.ActivityCarsBinding
import com.example.carsapp.ui.adapter.TabAdapter
import com.example.carsapp.ui.tab.TabLayout

internal class CarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarsBinding
    private val viewModel: CarsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        checkIfHasInternetConnection()
    }

    private fun setupBinding() {
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun checkIfHasInternetConnection() {
        when (CheckNetworkConnection(this@CarsActivity).isConnected()) {
            true -> internetConnectionSuccess()
            false -> internetConnectionError()
        }
    }

    private fun internetConnectionSuccess() {
        viewModel.viewState.observe(this) { state: CarsListState ->
            when (state) {
                CarsListState.Loading -> onLoading()
                CarsListState.Empty -> onEmpty()
                CarsListState.Error -> onError()
                is CarsListState.Loaded -> onLoaded(state)
            }
        }
    }

    private fun onLoading() {
        binding.loading.isVisible = true
        binding.carsViewPager.isVisible = false
        binding.carsErrorView.isVisible = false
    }

    private fun onEmpty() {
        binding.carsViewPager.isVisible = false
        binding.loading.isVisible = false
        binding.carsErrorView.apply {
            isVisible = true
            icon = R.drawable.baseline_clear_all_24
            description = context.getString(R.string.empty_list_description)
            actionLabelVisibility = false
        }
    }

    private fun onError() {
        binding.carsErrorView.isVisible = true
        binding.carsViewPager.isVisible = false
        binding.loading.isVisible = false

        binding.carsErrorView.apply {
            icon = R.drawable.ic_close
            description = context.getString(R.string.error_description)
            actionLabel = context.getString(R.string.try_again)

            setOnRetryButtonClickedListener {
                // When call api, you should create try to call API method and use for action here
                Toast.makeText(this@CarsActivity, "resultado da ação ao clicar em tentar novamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onLoaded(state: CarsListState.Loaded) {
        binding.carsViewPager.isVisible = true
        binding.loading.isVisible = false
        binding.carsErrorView.isVisible = false

        TabLayout.viewPager = binding.carsViewPager
        binding.tabLayout.addOnTabSelectedListener(TabLayout)
        binding.carsViewPager.adapter = TabAdapter(
            fragmentActivity = this,
            context = this,
            listOfCars = state.cars
        )
    }

    private fun internetConnectionError() {
        binding.carsViewPager.isVisible = false

        binding.carsErrorView.apply {
            actionLabelVisibility = true
            icon = R.drawable.ic_signal_wifi_off
            description = context.getString(R.string.internet_error_description)
            actionLabel = context.getString(R.string.try_again)

            setOnRetryButtonClickedListener {
                // Implement the action of call api again and load list of cars, this test internet connection.
                Toast.makeText(this@CarsActivity, "resultado da ação ao clicar em tentar novamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
