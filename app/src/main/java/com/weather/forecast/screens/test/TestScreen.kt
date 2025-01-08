package com.weather.forecast.screens.test

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.weather.forecast.R
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun TestScreen() {
    Scaffold{

    }

    SubcomposeLayout { constraints ->
        // 组合
        val measurable = subcompose(1) {
            Text("")
        }[0]
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }

    Box {

    }

    BoxWithConstraints {
        constraints
        if (minWidth >= 360.dp) {
            Text("xxx")
        } else {
            Text("yyy")
        }
    }
}

@Composable
fun CustomLayout(modifier: Modifier, content: @Composable () -> Unit) {
    Layout(content, modifier) { measurables, constraints ->
        var width = 0
        var height = 0
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints).also { placeable ->
                width = max(width, placeable.width)
                height += placeable.height
            }
        }
        layout(width, height) {
            var totalHeight = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(0, totalHeight)
                totalHeight += placeable.height
            }
        }
    }
}