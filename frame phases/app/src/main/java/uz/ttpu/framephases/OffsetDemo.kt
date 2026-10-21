@Composable
fun OffsetDemo() {
    var offsetX by remember { mutableStateOf(0.dp) }
    SideEffect { Log.d("Phases", "OffsetDemo: composition") }

    Column {
        Button(onClick = { offsetX = if (offsetX == 0.dp) 80.dp else 0.dp }) {
            Text("Move")
        }
        Text(
            text = "Offset demo",
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToPx(), 0) } // read in LAYOUT (placement step)
                .logPhases("offset demo"),
        )
    }
}

// Experiment version (read moved into composition), only used for the comparison in Q6
@Composable
fun OffsetDemoExperiment() {
    var offsetX by remember { mutableStateOf(0.dp) }
    SideEffect { Log.d("Phases", "OffsetDemo: composition") }

    Column {
        Button(onClick = { offsetX = if (offsetX == 0.dp) 80.dp else 0.dp }) {
            Text("Move")
        }
        val offsetPx = with(LocalDensity.current) { offsetX.roundToPx() }
        Text(
            text = "Offset demo",
            modifier = Modifier
                .offset { IntOffset(offsetPx, 0) } // the lambda now uses the Int
                .logPhases("offset demo"),
        )
    }
}
