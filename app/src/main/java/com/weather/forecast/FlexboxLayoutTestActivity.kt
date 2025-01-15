package com.weather.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import com.weather.forecast.widgets.ColoredTextView

/**
 * measureChildWithMargins()
 * https://github.com/google/flexbox-layout
 * FlowRow
 */
class FlexboxLayoutTestActivity : AppCompatActivity() {

    private val provinces = listOf(
        "北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "辽宁省", "吉林省",
        "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
        "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省",
        "云南省", "陕西省", "甘肃省", "青海省", "台湾省", "内蒙古自治区",
        "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区",
        "香港特别行政区", "澳门特别行政区"
    )

    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_compose)
        findViewById<ComposeView>(R.id.composeView)
            .setContent {
                FlowRow {
                    for (province in provinces) {
                        AndroidView(factory = { context ->
                            ColoredTextView(context, null).apply {
                                text = province
                            }
                        })
                    }
                }
            }
    }

}
