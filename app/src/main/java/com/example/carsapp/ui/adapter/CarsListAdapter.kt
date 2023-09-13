package com.example.carsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carsapp.databinding.CarsCardBinding
import com.example.carsapp.model.Car
import com.example.carsapp.ui.fragment.CarsListViewHolder

internal class CarsListAdapter(
    private val cars: List<Car>
) : RecyclerView.Adapter<CarsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarsCardBinding.inflate(inflater, parent, false)

        return CarsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarsListViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount(): Int = cars.size
}