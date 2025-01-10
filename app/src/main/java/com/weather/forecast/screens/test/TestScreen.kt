package com.weather.forecast.screens.test

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat.NestedScrollType
import com.weather.forecast.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestScreen() {
    Scaffold {

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


//val interactionSource = remember { MutableInteractionSource() }
//var offsetX by remember { mutableFloatStateOf(0f) }
//Text("xxx", Modifier.offset {
//    IntOffset(offsetX.roundToInt(), 0)
//}.draggable(rememberDraggableState { delta ->
//    println("$delta")
//    offsetX += delta
//}, Orientation.Horizontal, interactionSource = interactionSource))
//val isDragged by interactionSource.collectIsDraggedAsState()
//Text(if(isDragged) "拖动中" else "静止")

//    Modifier.scrollable(
//        rememberScrollableState {
//            println("$it")
//            it
//        }, Orientation.Horizontal,
//        overscrollEffect = ScrollableDefaults.overscrollEffect()
//    )
//    Modifier.verticalScroll()
//    Modifier.horizontalScroll()

//var offsetY by remember { mutableFloatStateOf(0f) }
//val dispatcher = remember { NestedScrollDispatcher() }
//Column(modifier = Modifier.draggable(
//    rememberDraggableState {
//        val consumed = dispatcher.dispatchPreScroll(Offset(0f, it), NestedScrollSource.Drag)
//        offsetY = it - consumed.y
//        dispatcher.dispatchPostScroll(0f, Offset(0f, 0f), NestedScrollSource.Drag)
//    }
//).nestedScroll(dispatcher))
}

@Composable
fun CustomLaunchedEffect(welcome: String) {
//    var rememberWelcome by remember { mutableStateOf(welcome) }
//    rememberWelcome = welcome

    val rememberWelcome by rememberUpdatedState(welcome)
    LaunchedEffect(Unit) {
        delay(3000)
        println("@@= $rememberWelcome")
    }

    val scope = rememberCoroutineScope()
    remember {
        scope.launch {  }
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