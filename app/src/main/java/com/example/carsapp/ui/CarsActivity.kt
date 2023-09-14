package com.example.carsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.carsapp.R
import com.example.carsapp.common.CheckNetworkConnection
import com.example.carsapp.databinding.ActivityCarsBinding
import com.example.carsapp.model.Car
import com.example.carsapp.ui.adapter.TabAdapter
import com.example.carsapp.ui.tab.TabLayout

internal class CarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarsBinding

    // TODO: remove mocked list after call api.
    private val listOfCars = listOf<Car>(
            Car(
                id = 1,
                price = "1000",
                battery = "1000",
                potency = "1000",
                recharge = "1000",
                photoUrl = "1000",
                isFavorite = false
            ),
            Car(
                id = 2,
                price = "2000",
                battery = "2000",
                potency = "2000",
                recharge = "2000",
                photoUrl = "2000",
                isFavorite = false
            ),
            Car(
                id = 3,
                price = "3000",
                battery = "3000",
                potency = "3000",
                recharge = "3000",
                photoUrl = "3000",
                isFavorite = false
            ),
            Car(
                id = 2,
                price = "4000",
                battery = "4000",
                potency = "4000",
                recharge = "4000",
                photoUrl = "4000",
                isFavorite = false
            )
    )

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
        binding.carsViewPager.isVisible = false
        binding.carsViewPager.adapter = TabAdapter(this, listOfCars)
    }

    private fun checkIfHasInternetConnection() {
        when (CheckNetworkConnection(this@CarsActivity).isConnected()) {
            true -> internetConnectionSuccess()
            false -> internetConnectionError()
        }
    }

    private fun internetConnectionSuccess() {
        when (listOfCars.isEmpty()) {
            true -> {
                binding.carsViewPager.isVisible = false
                binding.carsErrorView.apply {
                    root.isVisible = true
                    errorImage.setImageResource(R.drawable.baseline_clear_all_24)
                    errorDescriprion.text = "Desculpe, mas parece que não existem carros elétricos na lista."
                    tryAgainButton.isVisible = false
                }
            }

            false -> {
                binding.carsViewPager.isVisible = true
                TabLayout.viewPager = binding.carsViewPager
                binding.tabLayout.addOnTabSelectedListener(TabLayout)
            }
        }
    }

    private fun internetConnectionError() {
        // TODO: Setup internet error
        binding.carsViewPager.isVisible = false
        binding.carsErrorView.root.isVisible = true
        binding.carsErrorView.errorImage.setImageResource(R.drawable.ic_signal_wifi_off)
        binding.carsErrorView.errorDescriprion.text =
            "Desculpe, ocorreu um erro com a sua internet e já estamos tentando reconectar. Caso demore, por favor, tente novamente mais tarde."
        binding.carsErrorView.tryAgainButton.isVisible = true
        binding.carsErrorView.tryAgainButton.setOnClickListener {
            Toast.makeText(this, "Tentar recarregar a lista de carros", Toast.LENGTH_SHORT).show()
        }
    }
}