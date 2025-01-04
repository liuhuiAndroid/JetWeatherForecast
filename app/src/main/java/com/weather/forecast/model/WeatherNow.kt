package com.weather.forecast.model

import com.squareup.moshi.JsonClass

// 实时天气
@JsonClass(generateAdapter = true)
data class WeatherNow(
    // 状态码，标识请求的响应状态，通常是数字或字符串
    val code: String,

    // 当前数据的响应式页面链接，便于嵌入网站或应用
    val fxLink: String,

    // 当前天气数据
    val now: Now,

    // 数据来源和许可证等信息
    val refer: Refer,

    // 当前数据的最近更新时间
    val updateTime: String,
)