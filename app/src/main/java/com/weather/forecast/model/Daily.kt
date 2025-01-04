package com.weather.forecast.model

// 每日天气数据类
data class Daily(
    // 预报日期
    val fxDate: String,

    // 日出时间，高纬度地区可能为空
    val sunrise: String?,

    // 日落时间，高纬度地区可能为空
    val sunset: String?,

    // 当天月升时间，可能为空
    val moonrise: String?,

    // 当天月落时间，可能为空
    val moonset: String?,

    // 月相名称
    val moonPhase: String,

    // 月相图标代码，参考天气图标项目
    val moonPhaseIcon: String,

    // 预报当天最高温度
    val tempMax: Double,

    // 预报当天最低温度
    val tempMin: Double,

    // 预报白天天气状况的图标代码，参考天气图标项目
    val iconDay: String,

    // 预报白天天气状况文字描述，如阴晴雨雪等
    val textDay: String,

    // 预报夜间天气状况的图标代码，参考天气图标项目
    val iconNight: String,

    // 预报晚间天气状况文字描述，如阴晴雨雪等
    val textNight: String,

    // 预报白天风向360角度
    val wind360Day: Int,

    // 预报白天风向文字描述
    val windDirDay: String,

    // 预报白天风力等级
    val windScaleDay: String,

    // 预报白天风速，单位：公里/小时
    val windSpeedDay: Double,

    // 预报夜间风向360角度
    val wind360Night: Int,

    // 预报夜间风向文字描述
    val windDirNight: String,

    // 预报夜间风力等级
    val windScaleNight: String,

    // 预报夜间风速，单位：公里/小时
    val windSpeedNight: Double,

    // 预报当天总降水量，单位：毫米
    val precip: Double,

    // 紫外线强度指数
    val uvIndex: Int,

    // 相对湿度，百分比数值
    val humidity: Int,

    // 大气压强，单位：百帕
    val pressure: Double,

    // 能见度，单位：公里
    val vis: Double,

    // 云量，百分比数值。可能为空
    val cloud: Int?
)