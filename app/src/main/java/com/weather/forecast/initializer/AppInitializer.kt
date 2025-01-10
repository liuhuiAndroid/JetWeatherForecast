package com.weather.forecast.initializer

import android.content.Context
import androidx.startup.Initializer

class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) = Unit

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
        TimberInitializer::class.java,
        LocationInitializer::class.java,
    )
}
