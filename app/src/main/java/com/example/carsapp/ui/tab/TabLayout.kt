package com.example.carsapp.ui.tab

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

object TabLayout : TabLayout.OnTabSelectedListener {
    lateinit var viewPager: ViewPager2

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.let {
            viewPager.currentItem = it.position
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // Do nothing.
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // Do nothing.
    }
}