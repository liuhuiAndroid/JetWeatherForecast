package com.weather.forecast.model

// 数据来源和许可证信息类
data class Refer(
    // 数据来源，或数据源说明，可能为空
    val sources: List<String>,

    // 数据许可或版权声明，可能为空
    val license: List<String>
)