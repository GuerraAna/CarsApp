package com.example.carsapp

import com.example.carsapp.model.Car
import retrofit2.Call

internal class CarsListUseCase(
    private val repository: CarsListRepository = CarsListRepository()
) {
    suspend fun getCars(): List<Car> {
        return repository.getCars()
    }
}