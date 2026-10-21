@Composable
fun StackBelow(
    modifier: Modifier = Modifier,
    spacing: Dp = 8.dp,
    content: @Composable () -> Unit,
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->

        // Step 1: measure the children, each exactly once,
        // with loose minimum constraints.
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val placeables = measurables.map { measurable ->
            Log.d("StackBelow", "measure child")
            measurable.measure(loose)
        }

        // Step 2: decide our own size: the widest child, and the heights
        // plus the gaps between them.
        val spacingPx = spacing.roundToPx()
        val width = placeables.maxOfOrNull { it.width } ?: 0
        val gaps = if (placeables.isEmpty()) 0 else spacingPx * (placeables.size - 1)
        val height = placeables.sumOf { it.height } + gaps

        // Step 3: place the children, one below the other.
        layout(constraints.constrainWidth(width), constraints.constrainHeight(height)) {
            Log.d("StackBelow", "place children")
            var y = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = y)
                y += placeable.height + spacingPx
            }
        }
    }
}

@Composable
fun StackBelowDemo() {
    StackBelow(Modifier.fillMaxWidth(), spacing = 8.dp) {
        Box(Modifier.fillMaxWidth().height(120.dp).background(Color(0xFF6650A4)))
        Text("I'm below the image")
        // Text("Third child") // uncomment for the last part of Q14
    }
}
