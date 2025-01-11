package com.weather.forecast.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import timber.log.Timber

object LocationManager {

    @SuppressLint("StaticFieldLeak")
    private var mLocationClient: AMapLocationClient? = null

    fun getCurrentLocation(
        context: Context,
        onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
    ) {
        // Check if location permissions are granted
        if (areLocationPermissionsGranted(context)) {
            if (mLocationClient == null) {
                mLocationClient = AMapLocationClient(context)
                val mLocationListener = AMapLocationListener { location ->
                    if (location != null) {
                        Timber.tag("Location").i("@@@ => $location")
                        // If location is not null, invoke the success callback with latitude and longitude
                        onGetCurrentLocationSuccess(Pair(location.latitude, location.longitude))
                        mLocationClient?.stopLocation()
                    }
                }
                mLocationClient?.apply {
                    setLocationListener(mLocationListener)
                    setLocationOption(createLocationOption())
                }
            }
            mLocationClient?.startLocation()
        }
    }

    /**
     * 创建定位配置
     */
    private fun createLocationOption(): AMapLocationClientOption {
        return AMapLocationClientOption().apply {
            locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            isOnceLocation = true
            isOnceLocationLatest = true
        }
    }

    /**
     * 销毁 AMapLocationClient 以释放资源
     */
    fun destroyLocationClient() {
        mLocationClient?.onDestroy()
        mLocationClient = null
    }

    /**
     * Checks if location permissions are granted.
     *
     * @return true if both ACCESS_FINE_LOCATION and ACCESS_COARSE_LOCATION permissions are granted; false otherwise.
     */
    private fun areLocationPermissionsGranted(context: Context): Boolean {
        return (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }

}