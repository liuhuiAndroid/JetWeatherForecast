package com.weather.forecast.location

import android.content.Context
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import timber.log.Timber

object LocationManager {

    fun oneTime(context: Context) {
        val mLocationClient = AMapLocationClient(context)
        val mLocationOption = AMapLocationClientOption().apply {
            locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
            isOnceLocation = true
            isOnceLocationLatest = true
        }

        val mLocationListener = AMapLocationListener { location ->
            if (location != null) {
                Timber.tag("Location").i("@@@ => $location")
                mLocationClient.stopLocation()
                mLocationClient.onDestroy()
            }
        }
        mLocationClient.apply {
            setLocationListener(mLocationListener)
            setLocationOption(mLocationOption)
            startLocation()
        }
    }

}