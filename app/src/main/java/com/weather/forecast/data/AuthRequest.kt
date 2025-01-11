package com.weather.forecast.data

data class AuthRequest(
    val productId: Int,
    val deviceId: String,
    val password: String,
    val version: String,
)