@Composable
fun ParallaxSlow() {
    val listState = rememberLazyListState()
    val compositions = remember { intArrayOf(0) }
    SideEffect { Log.d("Parallax", "slow: composition #${++compositions[0]}") }

    Box {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF6650A4))
                .offset(
                    y = with(LocalDensity.current) {
                        (listState.firstVisibleItemScrollOffset / 2).toDp() // composition read
                    }
                )
        )
        LazyColumn(state = listState) {
            items(100) { index ->
                Text("Item $index", Modifier.fillMaxWidth().padding(16.dp))
            }
        }
    }
}

@Composable
fun ParallaxFast() {
    val listState = rememberLazyListState()
    val compositions = remember { intArrayOf(0) }
    SideEffect { Log.d("Parallax", "fast: composition #${++compositions[0]}") }

    Box {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF6650A4))
                .offset {
                    IntOffset(x = 0, y = listState.firstVisibleItemScrollOffset / 2) // layout read
                }
        )
        LazyColumn(state = listState) {
            items(100) { index ->
                Text("Item $index", Modifier.fillMaxWidth().padding(16.dp))
            }
        }
    }
}
