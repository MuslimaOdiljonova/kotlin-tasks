@Composable
fun LectureTree() {
    SideEffect { Log.d("Phases", "LectureTree: composition") }

    Row(Modifier.logPhases("Row")) {
        Box(Modifier.size(40.dp).background(Color.Gray).logPhases("Image"))
        Column(Modifier.logPhases("Column")) {
            Text("Title text", Modifier.logPhases("Text A"))
            Text("Subtitle", Modifier.logPhases("Text B"))
        }
    }
}
