@Composable
fun ColorDemo() {
    var color by remember { mutableStateOf(Color.Red) }
    SideEffect { Log.d("Phases", "ColorDemo: composition") }

    Column {
        Button(onClick = { color = if (color == Color.Red) Color.Blue else Color.Red }) {
            Text("Change color")
        }
        Canvas(Modifier.size(120.dp).logPhases("color demo")) {
            drawRect(color) // read in DRAWING
        }
    }
}

// Second part of Q7: background(Color) gets the value when the modifier is built
@Composable
fun ColorDemoBackground() {
    var color by remember { mutableStateOf(Color.Red) }
    SideEffect { Log.d("Phases", "ColorDemo: composition") }

    Column {
        Button(onClick = { color = if (color == Color.Red) Color.Blue else Color.Red }) {
            Text("Change color")
        }
        Box(Modifier.size(120.dp).background(color).logPhases("color demo")) // read in COMPOSITION
    }
}
