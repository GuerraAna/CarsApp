package com.example.carsapp

import com.example.carsapp.model.Car

/** States when call list of cars. */
internal sealed class CarsListState {
    data object Loading: CarsListState()
    data object Empty: CarsListState()
    data object Error: CarsListState()
    data class Loaded(val cars: List<Car>): CarsListState()
}
