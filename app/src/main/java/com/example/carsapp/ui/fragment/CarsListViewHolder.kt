package com.example.carsapp.ui.fragment

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.carsapp.R
import com.example.carsapp.databinding.CarsCardBinding
import com.example.carsapp.model.Car

/** View Holder to bind response of API with cars card. */
internal class CarsListViewHolder(
    private val context: Context,
    private val binding: CarsCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car) {
        // TODO: Remember to remove and use photo url of car data - Glide can help you.
        binding.carImage.setImageResource(R.drawable.eletric_car)

        binding.apply {
            carPrice.text = context.getString(R.string.price_label, car.price)
            carBattery.text = context.getString(R.string.battery_label, car.battery)
            carPotency.text = context.getString(R.string.potency_label, car.potency)
            carRecharge.text = context.getString(R.string.recharge_label, car.recharge)
        }
    }
}
