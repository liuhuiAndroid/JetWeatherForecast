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
