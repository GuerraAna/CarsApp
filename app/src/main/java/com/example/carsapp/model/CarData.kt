package com.example.carsapp.model

/** Cars characteristics*/
internal data class Car(
    val id: Int,
    val price: String,
    val battery: String,
    val potency: String,
    val recharge: String,
    val photoUrl: String,
    var isFavorite: Boolean
)