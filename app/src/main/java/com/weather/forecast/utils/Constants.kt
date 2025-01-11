package com.weather.forecast.utils

import com.weather.forecast.BuildConfig

object Constants {
    const val BASE_URL = "http://121.236.214.147:8991/"
    const val API_KEY = BuildConfig.API_KEY
    const val contentType: String = "application/json"

    // 产品Id，CTWing平台产品概况中获取。
    const val productId: Int = 10270184

    // 设备Id，CTWing平台设备信息管理中获取。
    const val deviceId: String = "72179592170348552"

    // 设备特征串。CTWing平台生成。可在设备信息管理-认证信息查询。
    const val password: String = "CgDh3EznTx3YNlhK6FALT8Zi02lYsZ5kRd8fHabdpt0"

    // MasterKey为平台上创建产品时生成，可在产品概况中查询。
    const val masterKey: String = "838acd15cb854c6ba8005d0b95c48f7c"

    // 上报主题。非透传产品(物模型)，为服务定义生成的服务标识；透传产品可任意填写。
    const val topic: String = "???"
    const val version: String = "1.0"
}
