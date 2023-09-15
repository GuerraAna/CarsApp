package com.example.carsapp

import com.example.carsapp.model.Car

internal sealed class CarsListState {
    object Loading: CarsListState()
    object Empty: CarsListState()
    object Error: CarsListState()
    data class Loaded(val cars: List<Car>): CarsListState()
}