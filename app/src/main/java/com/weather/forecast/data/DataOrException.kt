package com.weather.forecast.data

class DataOrException<T>(
    var data: T? = null,
    var isLoading: Boolean = false,
    var error: Error = Error(),
)