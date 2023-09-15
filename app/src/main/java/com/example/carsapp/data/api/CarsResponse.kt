package com.example.carsapp.data.api

import com.example.carsapp.model.Car

/** Cars Response when call some API. */
internal interface CarsResponse {
    // Unused interface
    val cars: List<Car>
}
