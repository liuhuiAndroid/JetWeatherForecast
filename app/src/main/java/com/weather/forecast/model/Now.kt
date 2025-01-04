package com.weather.forecast.model

// 当前天气数据类
data class Now(
    // 数据观测时间
    val obsTime: String,

    // 温度，默认单位：摄氏度
    val temp: Double,

    // 体感温度，默认单位：摄氏度
    val feelsLike: Double,

    // 天气状况的图标代码，参考天气图标项目
    val icon: String,

    // 天气状况的文字描述，如阴晴雨雪等
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

    // 过去1小时的降水量，单位：毫米
    val precip: Double,

    // 大气压强，单位：百帕
    val pressure: Double,

    // 能见度，单位：公里
    val vis: Double,

    // 云量，百分比数值。可能为空
    val cloud: Int?,

    // 露点温度，单位：摄氏度。可能为空
    val dew: Double?
)
