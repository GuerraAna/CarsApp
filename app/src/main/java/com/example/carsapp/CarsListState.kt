package com.example.carsapp

import com.example.carsapp.model.Car

/** States when call list of cars. */
internal sealed class CarsListState {
    /** When request cars list, should show loading state. */
    data object Loading: CarsListState()

    /** When requested cars list is empty, should show empty state. */
    data object Empty: CarsListState()

    /** When requested cars list is an error, should show error state. */
    data object Error: CarsListState()

    /** When requested cars list is loaded, should show loaded list with cars response. */
    data class Loaded(val cars: List<Car>): CarsListState()
}
