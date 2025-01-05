package com.weather.forecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.weather.forecast.R

// https://juejin.cn/post/7450399434880057379
@Preview
@Composable
private fun NineGridLayoutPreview() {
    NineGridLayout(modifier = Modifier.background(Color.Gray), itemSize = 100.dp, padding = 10.dp) {
        repeat(9) {
            Image(
                painter = painterResource(id = R.drawable.sun), contentDescription = null,
            )
        }
    }
}

/**
 * 九宫格布局
 *
 * @param singleItemSize 单个元素的大小
 * @param itemSize 其他元素的大小
 * @param padding 元素之间的间距
 * @param content 元素内容
 */
@Composable
fun NineGridLayout(
    modifier: Modifier = Modifier,
    singleItemSize: Dp = 200.dp,
    itemSize: Dp = 100.dp,
    padding: Dp = 8.dp,
    content: @Composable @UiComposable () -> Unit
) {
    // 使用 Layout 进行自定义布局
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        // 在 Layout 内部获取 Density 并计算 px 值
        val paddingPx = padding.roundToPx()
        val itemSizePx = itemSize.roundToPx()
        val singleSizePx = singleItemSize.roundToPx()

        // 子组件的数量
        when (measurables.size) {
            1 -> {
                // 只有一个元素时，使用固定大小进行布局
                // 测量出结果并且放置
                val placeable = measurables.first().measure(constraints)
                layout(singleSizePx, singleSizePx) {
                    placeable.placeRelative(0, 0)
                }
            }

            else -> {
                // 不止一个组件时，要计算数量来决定高度和宽度，这部分是通用代码就不过多解释了。
                // 计算行数和总宽高，避免重复计算
                val rowCount = (measurables.size + 2) / 3
                val totalWidth = (itemSizePx + paddingPx) * 3 - paddingPx
                val totalHeight = (itemSizePx + paddingPx) * rowCount - paddingPx

                // 测量每个元素
                val placeables = measurables.map { it.measure(Constraints.fixed(itemSizePx, itemSizePx)) }

                // 布局逻辑：按 3 列排列
                layout(totalWidth, totalHeight) {
                    // 遍历所有元素，计算位置并放置
                    placeables.forEachIndexed { index, placeable ->
                        val xPosition = (index % 3) * (itemSizePx + paddingPx)
                        val yPosition = (index / 3) * (itemSizePx + paddingPx)
                        placeable.placeRelative(xPosition, yPosition)
                    }
                }
            }
        }
    }
}
