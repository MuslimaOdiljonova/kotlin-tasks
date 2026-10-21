// Step (1): value version
@Composable
fun ParentByValue() {
    var count by remember { mutableIntStateOf(0) }
    SideEffect { Log.d("Skip", "Parent: composition") }

    Column {
        Button(onClick = { count++ }) { Text("Add") }
        ChildByValue(count) // count is read HERE, in Parent's restart scope
        Sibling()
    }
}

@Composable
fun ChildByValue(count: Int) {
    SideEffect { Log.d("Skip", "Child: composition") }
    Text("Count: $count")
}

// Step (2): lambda version
@Composable
fun Parent() {
    var count by remember { mutableIntStateOf(0) }
    SideEffect { Log.d("Skip", "Parent: composition") }

    Column {
        Button(onClick = { count++ }) { Text("Add") }
        Child(count = { count }) // pass a lambda that returns the value
        Sibling()
    }
}

@Composable
fun Child(count: () -> Int) {
    SideEffect { Log.d("Skip", "Child: composition") }
    Text("Count: ${count()}") // the state is read here, not in Parent
}

@Composable
fun Sibling() {
    SideEffect { Log.d("Skip", "Sibling: composition") }
    Text("I never change")
}
