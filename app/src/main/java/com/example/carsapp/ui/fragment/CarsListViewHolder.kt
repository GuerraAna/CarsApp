package com.example.carsapp.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import com.example.carsapp.R
import com.example.carsapp.databinding.CarsCardBinding
import com.example.carsapp.model.Car

internal class CarsListViewHolder(
    private val binding: CarsCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car) {
        // TODO: Remember to remove and use photo url of car data - Glide can help you.
        binding.carImage.setImageResource(R.drawable.eletric_car)

        // TODO: Remember to transfer strings value for string resource.
        binding.carPrice.text = "Preço: R$ ${car.price}"
        binding.carBattery.text = "Bateria: ${car.battery} kWh"
        binding.carPotency.text = "Potência: ${car.potency}cv"
        binding.carRecharge.text = "Recarga: ${car.recharge} min"
    }
}