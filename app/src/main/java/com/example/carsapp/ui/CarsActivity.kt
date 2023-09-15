package com.example.carsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
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
            description = "Desculpe, mas parece que não existem carros elétricos na lista."
            actionLabelVisibility = false
        }
    }

    private fun onError() {
        binding.carsErrorView.isVisible = true
        binding.carsViewPager.isVisible = false
        binding.loading.isVisible = false

        binding.carsErrorView.apply {
            icon = R.drawable.ic_close
            description = "Desculpe, ocorreu algum erro e já estamos tentando retornar."
            actionLabel = "Tentar novamente testando texto"
            setOnRetryButtonClickedListener {
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
        binding.carsViewPager.adapter = TabAdapter(this, state.cars)
    }

    private fun internetConnectionError() {
        binding.carsViewPager.isVisible = false

        binding.carsErrorView.apply {
            actionLabelVisibility = true
            icon = R.drawable.ic_signal_wifi_off
            description = "Desculpe, ocorreu um erro com a sua internet e já estamos tentando reconectar. Caso demore, por favor, tente novamente mais tarde."
            actionLabel = "Tentar novamente testando texto"

            setOnRetryButtonClickedListener {
                Toast.makeText(this@CarsActivity, "resultado da ação ao clicar em tentar novamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
