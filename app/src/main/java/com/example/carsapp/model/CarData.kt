package com.example.carsapp.model

/**
 * Cars characteristics
 * @param id Int Identify the car.
 * @param price String Identify the amount of the car.
 * @param battery String Identify battery capacity in kWh.
 * @param potency String Identify car power in cv.
 * @param recharge String Identify the time to recharge full battery.
 * @param photoUrl String Identify the design of the car.
 * @param isFavorite Boolean Allows the car to be favorite.
 * */
internal data class Car(
    val id: Int,
    val price: String,
    val battery: String,
    val potency: String,
    val recharge: String,
    val photoUrl: String,
    var isFavorite: Boolean
)
