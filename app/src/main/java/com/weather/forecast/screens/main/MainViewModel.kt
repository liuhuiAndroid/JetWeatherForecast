package com.weather.forecast.screens.main

import androidx.lifecycle.ViewModel
import com.weather.forecast.data.DataOrException
import com.weather.forecast.data.Error
import com.weather.forecast.model.WeatherData
import com.weather.forecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(location: String): DataOrException<WeatherData> {
        return try {
            coroutineScope {
                val weatherNowDeferred = async { repository.getWeatherNow(location) }
                val weather7dDeferred = async { repository.getWeather7d(location) }
                val weather24hDeferred = async { repository.getWeather24h(location) }
                val authDeferred = async { repository.auth("signKey123") }
                val auth = authDeferred.await()
                val weatherNow = weatherNowDeferred.await()
                val weather7d = weather7dDeferred.await()
                val weather24h = weather24hDeferred.await()
                if (weatherNow.data != null && weather7d.data != null && weather24h.data != null) {
                    DataOrException(
                        data = WeatherData(
                            weatherNow.data!!,
                            weather7d.data!!,
                            weather24h.data!!
                        )
                    )
                } else {
                    DataOrException(error = Error(-1, "Failed to fetch weather data"))
                }
            }
        } catch (e: Exception) {
            DataOrException(error = Error(-1, e.message))
        }
    }

}