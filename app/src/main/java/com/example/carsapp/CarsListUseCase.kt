package com.example.carsapp

import com.example.carsapp.model.Car

/** Use case layer for cars list feature. */
internal class CarsListUseCase(
    private val repository: CarsListRepository = CarsListRepository()
) {

    suspend fun getCars(): List<Car> = repository.getCars()
}
