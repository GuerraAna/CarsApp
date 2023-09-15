package com.example.carsapp

import com.example.carsapp.data.CarMock
import com.example.carsapp.model.Car

/**
 * Repository layer for the cars list feature.
 */
internal class CarsListRepository {

    // TODO: Update return to CarsResponse
    suspend fun getCars(): List<Car> = CarMock.listOfCars
}
