package com.example.carsapp.ui.fragment

import android.content.Context
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

/**
 * List of cars fragment creation to use in view pager.
 * @param listOfCars List<Car> receipt of API response.
 */
internal class ListOfCarsFragment(
    private val context: Context,
    private val listOfCars: List<Car>
) : Fragment() {

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

        binding.carsList.layoutManager = LinearLayoutManager(context)
        binding.carsList.adapter = CarsListAdapter(context, listOfCars)

        binding.carsFabButton.setOnClickListener {
            // Implement the action to save the selected card as favorite
            Toast.makeText(context, "Adicionar carro elétrico", Toast.LENGTH_SHORT).show()
        }
    }
}
