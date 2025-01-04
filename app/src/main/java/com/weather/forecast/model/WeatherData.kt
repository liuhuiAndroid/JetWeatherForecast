package com.weather.forecast.model

data class WeatherData(
    val weatherNow: WeatherNow,
    val weather7d: Weather7d,
    val weather24h: Weather24h,
)