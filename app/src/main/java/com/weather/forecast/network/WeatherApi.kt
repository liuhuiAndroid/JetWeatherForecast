package com.weather.forecast.network

import com.weather.forecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton
import com.weather.forecast.model.WeatherNow
import com.weather.forecast.model.Weather7d
import com.weather.forecast.model.Weather24h

@Singleton
interface WeatherApi {

    // 城市搜索
    // curl -H "X-QW-Api-Key: API_KEY" --compressed 'https://geoapi.qweather.com/v2/city/lookup?location=shanghai'

    // 实时天气
    // curl -H "X-QW-Api-Key: API_KEY" --compressed 'https://api.qweather.com/v7/weather/now?location=101010100'
    // curl -H "X-QW-Api-Key: bd1d9db92f7543849b834b09f046dea4" --compressed 'https://api.qweather.com/v7/weather/now?location=101010100'
    @GET(value = "v7/weather/now")
    suspend fun getWeatherNow(
        @Query("location") location: String,
        @Query("key") key: String = Constants.API_KEY,
    ): WeatherNow

    // 每日天气预报/7天预报
    // curl -H "X-QW-Api-Key: API_KEY" --compressed 'https://api.qweather.com/v7/weather/7d?location=101010100'
    @GET(value = "v7/weather/7d")
    suspend fun getWeather7d(
        @Query("location") location: String,
        @Query("key") key: String = Constants.API_KEY,
    ): Weather7d

    // 逐小时天气预报/未来24小时
    // curl -H "X-QW-Api-Key: API_KEY" --compressed 'https://api.qweather.com/v7/weather/24h?location=101010100'
    @GET(value = "v7/weather/24h")
    suspend fun getWeather24h(
        @Query("location") location: String,
        @Query("key") key: String = Constants.API_KEY,
    ): Weather24h
}