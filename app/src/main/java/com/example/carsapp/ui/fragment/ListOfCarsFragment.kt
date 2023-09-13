package com.example.carsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carsapp.databinding.CarsFragmentBinding
import com.example.carsapp.model.Car
import com.example.carsapp.ui.adapter.CarsListAdapter

internal class ListOfCarsFragment : Fragment() {

    private lateinit var binding: CarsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CarsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfCars = listOf(
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

        binding.carsList.layoutManager = LinearLayoutManager(context)
        binding.carsList.adapter = CarsListAdapter(listOfCars)

        binding.carsFabButton.setOnClickListener {
            Toast.makeText(context, "Adicionar carro elétrico", Toast.LENGTH_SHORT).show()
        }
    }
}