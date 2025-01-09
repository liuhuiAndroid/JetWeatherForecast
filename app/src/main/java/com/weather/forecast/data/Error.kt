package com.weather.forecast.data

data class Error(
    val errorCode: Int = 0,
    val errorMessage: String? = "",
) {
    override fun toString(): String = "$errorCode $errorMessage"
}