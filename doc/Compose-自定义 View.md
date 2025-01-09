Modifier.drawBehind {  }

Modifier.drawWithContent {
    drawRect(Color.Yellow)
    drawContent()
}

Canvas(Modifier.size(80.dp)){
    val imageResource = ImageBitmap.imageResource(R.drawable.sun)

    rotate(30f){
        drawImage(
            imageResource,
            dstSize = IntSize(size.width.roundToInt(), size.width.roundToInt())
        )
    }
}

Canvas(Modifier.size(80.dp)).graphicsLayer{

}

Canvas(Modifier.size(80.dp)){
    drawIntoCanvas{
        it.drawImageRect(image,
            dstSize = IntSize(size.width.roundToInt(), size.width.roundToInt()),
            paint = paint)
    }
}

// --------------
Modify.layout { measurable, constraints ->

}

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

// --------------
// 测量阶段调用 Composable 函数
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

BoxWithConstraints {
    constraints
    if (minWidth >= 360.dp) {
        Text("xxx")
    } else {
        Text("yyy")
    }
}

Scaffold
LazyColumn
// --------------
触摸反馈
Modifier.pointerInput(Unit){
    awaitEachGesture {
        val event = awaitPointerEvent()
    }
}

// --------------
val listState = rememberLazyListState()
LazyColumn(state = listState) {
    item {

    }
}
val scope = rememberCoroutineScope()
Button(onClick = {
    scope.launch {
        listState.animateScrollToItem()
    }
}) { }

val interactionSource = remember { MutableInteractionSource() }
var offsetX by remember { mutableFloatStateOf(0f) }
Text("xxx", Modifier.offset {
    IntOffset(offsetX.roundToInt(), 0)
}.draggable(rememberDraggableState { delta ->
    println("$delta")
    offsetX += delta
}, Orientation.Horizontal, interactionSource = interactionSource))
val isDragged by interactionSource.collectIsDraggedAsState()
Text(if(isDragged) "拖动中" else "静止")

// 惯性滑动、嵌套滑动
Modifier.scrollable(
    rememberScrollableState {
        println("$it")
        it
    }, Orientation.Horizontal,
    overscrollEffect = ScrollableDefaults.overscrollEffect()
)
Modifier.verticalScroll()
Modifier.horizontalScroll()

// --------------
Modifier.nestedScroll()