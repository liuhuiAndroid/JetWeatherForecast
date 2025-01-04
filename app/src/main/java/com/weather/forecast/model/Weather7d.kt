package com.weather.forecast.model

import com.squareup.moshi.JsonClass

// 7天天气预报数据类
@JsonClass(generateAdapter = true)
data class Weather7d(
    // 状态码，标识请求的响应状态
    val code: String,

    // 每日天气预报数据列表
    val daily: List<Daily>,

    // 当前数据的响应式页面链接，便于嵌入网站或应用
    val fxLink: String,

    // 数据来源和许可证等信息
    val refer: Refer,

    // 当前API的最近更新时间
    val updateTime: String
)