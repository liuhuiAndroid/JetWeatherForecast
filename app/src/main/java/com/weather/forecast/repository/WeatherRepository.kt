package com.weather.forecast.repository

import com.weather.forecast.data.DataOrException
import com.weather.forecast.model.Weather24h
import com.weather.forecast.model.Weather7d
import com.weather.forecast.model.WeatherNow
import com.weather.forecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeatherNow(location: String)
            : DataOrException<WeatherNow, Boolean, Exception> {
        val response = try {
            api.getWeatherNow(location = location)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    suspend fun getWeather7d(location: String)
            : DataOrException<Weather7d, Boolean, Exception> {
        val response = try {
            api.getWeather7d(location = location)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    suspend fun getWeather24h(location: String)
            : DataOrException<Weather24h, Boolean, Exception> {
        val response = try {
            api.getWeather24h(location = location)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

}