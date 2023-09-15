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
        setupCarsDefaultLayout()
        checkIfHasInternetConnection()
    }

    private fun setupBinding() {
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupCarsDefaultLayout() {
        binding.title
    }

    private fun checkIfHasInternetConnection() {
        when (CheckNetworkConnection(this@CarsActivity).isConnected()) {
            true -> internetConnectionSuccess()
            false -> internetConnectionError()
        }
    }

    private fun internetConnectionSuccess() {
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.viewState.observe(this) { state: CarsListState ->
            when (state) {
                CarsListState.Loading -> {
                    binding.loading.isVisible = true
                    binding.carsViewPager.isVisible = false
                    binding.carsErrorView.isVisible = false
                }

                CarsListState.Empty -> {
                    binding.carsViewPager.isVisible = false
                    binding.loading.isVisible = false
                    binding.carsErrorView.apply {
                        isVisible = true
                        icon = R.drawable.baseline_clear_all_24
                        description = "Desculpe, mas parece que não existem carros elétricos na lista."
                        actionLabelVisibility = false
                    }
                }

                CarsListState.Error -> {
                    binding.carsErrorView.isVisible = true
                    binding.carsViewPager.isVisible = false
                    binding.loading.isVisible = false
                    binding.carsErrorView.icon = R.drawable.ic_close
                    binding.carsErrorView.description = "Desculpe, ocorreu algum erro e já estamos tentando retornar."
                    binding.carsErrorView.actionLabel = "Tentar novamente testando texto"
                    binding.carsErrorView.setOnRetryButtonClickedListener {
                        Toast.makeText(this, "resultado da ação ao clicar em tentar novamente", Toast.LENGTH_SHORT).show()
                    }
                }

                is CarsListState.Loaded -> {
                    binding.carsViewPager.isVisible = true
                    binding.loading.isVisible = false
                    binding.carsErrorView.isVisible = false
                    TabLayout.viewPager = binding.carsViewPager
                    binding.tabLayout.addOnTabSelectedListener(TabLayout)
                    binding.carsViewPager.adapter = TabAdapter(this, state.cars)
                }
            }
        }
    }

    private fun internetConnectionError() {
        // TODO: Setup internet error
        binding.carsViewPager.isVisible = false
        binding.carsErrorView.actionLabelVisibility = true
        binding.carsErrorView.icon = R.drawable.ic_signal_wifi_off
        binding.carsErrorView.description = "Desculpe, ocorreu um erro com a sua internet e já estamos tentando reconectar. Caso demore, por favor, tente novamente mais tarde."
        binding.carsErrorView.actionLabel = "Tentar novamente testando texto"
        binding.carsErrorView.setOnRetryButtonClickedListener {
            Toast.makeText(this, "resultado da ação ao clicar em tentar novamente", Toast.LENGTH_SHORT).show()
        }
    }
}