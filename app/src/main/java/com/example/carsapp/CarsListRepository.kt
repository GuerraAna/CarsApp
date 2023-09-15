package com.example.carsapp

import com.example.carsapp.data.CarMock
import com.example.carsapp.model.Car

/** Repository layer for the cars list feature. */
internal class CarsListRepository {

    // TODO 1.0: Call some api in the future.
    suspend fun getCars(): List<Car> = CarMock.listOfCars
}
