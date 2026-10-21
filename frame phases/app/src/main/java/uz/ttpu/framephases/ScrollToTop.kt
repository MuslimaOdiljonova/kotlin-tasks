@Composable
fun ScrollToTopNaive() {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val compositions = remember { intArrayOf(0) }
    SideEffect { Log.d("Derived", "naive: composition #${++compositions[0]}") }

    val showButton = listState.firstVisibleItemIndex > 0

    Box(Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(200) { index ->
                Text("Item $index", Modifier.fillMaxWidth().padding(16.dp))
            }
        }
        if (showButton) {
            Button(
                onClick = { scope.launch { listState.animateScrollToItem(0) } },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            ) {
                Text("Top")
            }
        }
    }
}

@Composable
fun ScrollToTopDerived() {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val compositions = remember { intArrayOf(0) }
    SideEffect { Log.d("Derived", "derived: composition #${++compositions[0]}") }

    // only notifies when the Boolean result changes, not on every index change
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    Box(Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(200) { index ->
                Text("Item $index", Modifier.fillMaxWidth().padding(16.dp))
            }
        }
        if (showButton) {
            Button(
                onClick = { scope.launch { listState.animateScrollToItem(0) } }, // TODO 3 done
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            ) {
                Text("Top")
            }
        }
    }
}
