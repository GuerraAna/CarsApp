package com.example.carsapp.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.carsapp.model.Car
import com.example.carsapp.ui.fragment.ListOfCarsFragment
import com.example.carsapp.ui.fragment.ListOfFavoriteCarsFragment

/** Adapter to control result of tab item clicked in Tab layout. */
internal class TabAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context,
    private val listOfCars: List<Car>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ListOfCarsFragment(context, listOfCars)
            1 -> ListOfFavoriteCarsFragment()
            else -> ListOfCarsFragment(context, listOfCars)
        }
    }

    override fun getItemCount(): Int = 2
}
