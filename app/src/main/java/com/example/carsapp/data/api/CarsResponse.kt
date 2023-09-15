package com.example.carsapp.data.api

import androidx.versionedparcelable.ParcelField
import com.example.carsapp.model.Car
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.Call

internal interface CarsResponse {
    val cars: List<Car>
}