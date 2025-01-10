package com.weather.forecast.initializer

import android.content.Context
import androidx.startup.Initializer
import com.amap.api.location.AMapLocationClient

class LocationInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        AMapLocationClient.updatePrivacyShow(context, true, true)
        AMapLocationClient.updatePrivacyAgree(context, true)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}