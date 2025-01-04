package com.weather.forecast.model

import com.squareup.moshi.JsonClass

// 每小时天气数据类
@JsonClass(generateAdapter = true)
data class Hourly(
    // 预报时间
    val fxTime: String,

    // 温度，默认单位：摄氏度
    val temp: Double,

    // 天气状况的图标代码，参考天气图标项目
    val icon: String,

    // 天气状况文字描述，如阴晴雨雪等
    val text: String,

    // 风向360角度（0-360），表示风的方位
    val wind360: Int,

    // 风向文字描述
    val windDir: String,

    // 风力等级
    val windScale: String,

    // 风速，单位：公里/小时
    val windSpeed: Double,

    // 相对湿度，百分比数值
    val humidity: Int,

    // 当前小时累计降水量，单位：毫米
    val precip: Double,

    // 逐小时预报降水概率，百分比数值，可能为空
    val pop: Int?,

    // 大气压强，单位：百帕
    val pressure: Double,

    // 云量，百分比数值。可能为空
    val cloud: Int?,

    // 露点温度，单位：摄氏度。可能为空
    val dew: Double?
)