package com.weather.forecast.data

data class AuthResponse(
    val code: String,
    val message: String,
    val accessToken: String,
)