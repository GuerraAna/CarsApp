package com.example.carsapp.data

import com.example.carsapp.model.Car

/** Mock of cars. This simulate the future API call. */
internal object CarMock {

    val listOfCarsEmpty = listOf<Car>()

    val listOfCars = listOf(
        Car(
            id = 1,
            price = "1000",
            battery = "1000",
            potency = "1000",
            recharge = "1000",
            photoUrl = "1000",
            isFavorite = false
        ),
        Car(
            id = 2,
            price = "2000",
            battery = "2000",
            potency = "2000",
            recharge = "2000",
            photoUrl = "2000",
            isFavorite = false
        ),
        Car(
            id = 3,
            price = "3000",
            battery = "3000",
            potency = "3000",
            recharge = "3000",
            photoUrl = "3000",
            isFavorite = false
        ),
        Car(
            id = 2,
            price = "4000",
            battery = "4000",
            potency = "4000",
            recharge = "4000",
            photoUrl = "4000",
            isFavorite = false
        )
    )
}
