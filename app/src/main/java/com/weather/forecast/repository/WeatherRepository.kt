package com.weather.forecast.repository

import com.weather.forecast.data.AuthRequest
import com.weather.forecast.data.DataOrException
import com.weather.forecast.data.Error
import com.weather.forecast.model.Weather24h
import com.weather.forecast.model.Weather7d
import com.weather.forecast.model.WeatherNow
import com.weather.forecast.network.WeatherApi
import com.weather.forecast.utils.Constants
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeatherNow(location: String): DataOrException<WeatherNow> {
        val response = try {
            api.getWeatherNow(location = location)
        } catch (e: Exception) {
            return DataOrException(error = Error(-1, e.message))
        }
        return DataOrException(data = response)
    }

    suspend fun getWeather7d(location: String): DataOrException<Weather7d> {
        val response = try {
            api.getWeather7d(location = location)
        } catch (e: Exception) {
            return DataOrException(error = Error(-1, e.message))
        }
        return DataOrException(data = response)
    }

    suspend fun getWeather24h(location: String): DataOrException<Weather24h> {
        val response = try {
            api.getWeather24h(location = location)
        } catch (e: Exception) {
            return DataOrException(error = Error(-1, e.message))
        }
        return DataOrException(data = response)
    }

    suspend fun auth(
        signKey: String,
    ): DataOrException<Any> {
        val timestamp = System.currentTimeMillis()
        val response = try {
            api.auth(
                signKey,
                "$timestamp",
                createSignature(signKey, Constants.masterKey, timestamp),
                AuthRequest(
                    Constants.productId,
                    Constants.deviceId,
                    Constants.password,
                    Constants.version
                )
            )
        } catch (e: Exception) {
            return DataOrException(error = Error(-1, e.message))
        }
        return DataOrException(data = response)
    }

    private fun createSignature(signKey: String, masterKey: String, timestamp: Long): String {
        val data = signKey + masterKey + timestamp.toString()
        val dataBytes = data.toByteArray(StandardCharsets.UTF_8)
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(dataBytes)
        val hexString = StringBuilder()
        val var10 = hash
        val var11 = hash.size

        for (var12 in 0 until var11) {
            val b = var10[var12]
            val hex = Integer.toHexString(255 and b.toInt())
            if (hex.length == 1) {
                hexString.append('0')
            }
            hexString.append(hex)
        }
        return hexString.toString()
    }

}