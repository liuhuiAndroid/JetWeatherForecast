package com.weather.forecast.model

// 24小时天气预报数据类
data class Weather24h(
    // 状态码，标识请求的响应状态
    val code: String,

    // 当前数据的响应式页面链接，便于嵌入网站或应用
    val fxLink: String,

    // 每小时天气预报数据列表
    val hourly: List<Hourly>,

    // 数据来源和许可证等信息
    val refer: Refer,

    // 当前API的最近更新时间
    val updateTime: String
)
