package com.weather.forecast.di

import android.content.Context
import com.weather.forecast.network.WeatherApi
import com.weather.forecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        val clientBuild =
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .dispatcher(Dispatcher().apply {
                    maxRequestsPerHost = 10
                })
        clientBuild.addInterceptor(HttpLoggingInterceptor { message ->
            Timber.tag("OKHttp-----").i(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuild.build())
            .build()
            .create(WeatherApi::class.java)
    }


}