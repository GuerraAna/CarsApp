package com.example.carsapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.carsapp.ui.fragment.ListOfCarsFragment
import com.example.carsapp.ui.fragment.ListOfFavoriteCarsFragment

internal class TabAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ListOfCarsFragment()
            1 -> ListOfFavoriteCarsFragment()

            else -> {
                // TODO: Testar a saída referente ao else. "o que é?". Momentaneamente, mantive o adapter principal como padrão.
                ListOfCarsFragment()
            }
        }
    }

    override fun getItemCount(): Int = 2
}