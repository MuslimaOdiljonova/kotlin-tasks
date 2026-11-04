package uz.ttpu.seminar_4b

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun Task_16(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        repeat(40) { index ->
            Text(text = "Setting ${index + 1}", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun Task_17(modifier: Modifier = Modifier) {
    val categories = listOf("All", "Electronics", "Books", "Clothes", "Toys", "Sports", "Garden")

    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            FilterChip(
                selected = category == "All",
                onClick = { },
                label = { Text(category) }
            )
        }
    }
}

@Composable
fun Task_18(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier.testTag("submit_button")
    ) {
        Text("Submit")
    }
}

@Composable
fun Task_19(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("Section one")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Warning"
            )
            Text("Password is too short")
        }
    }
}

@Composable
fun Task_20(isVisible: Boolean, modifier: Modifier = Modifier) {
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        label = "fade"
    )

    Card(
        modifier = modifier.graphicsLayer {
            alpha = animatedAlpha
        }
    ) {
        Text("I fade in and out", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun Task_21(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "spinner")
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)),
        label = "angle"
    )
    val scaleFactor by transition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse),
        label = "scale"
    )

    CircularProgressIndicator(
        progress = { 0.75f },
        modifier = modifier.graphicsLayer {
            rotationZ = angle
            scaleX = scaleFactor
            scaleY = scaleFactor
        }
    )
}

@Composable
fun Task_22(isBeingDragged: Boolean, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .size(120.dp)
                .zIndex(if (isBeingDragged) 1f else 0f)
        ) {
            Text("First", modifier = Modifier.padding(8.dp))
        }
        Card(
            modifier = Modifier
                .padding(start = 40.dp, top = 40.dp)
                .size(120.dp)
        ) {
            Text("Second", modifier = Modifier.padding(8.dp))
        }
    }
}

fun Modifier.dashedBorder(color: Color, strokeWidth: Dp): Modifier = this.drawBehind {
    val width = strokeWidth.toPx()
    drawRect(
        color = color,
        topLeft = Offset(width / 2, width / 2),
        size = size.copy(width = size.width - width, height = size.height - width),
        style = Stroke(
            width = width,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
        )
    )
}

@Composable
fun Task_23(modifier: Modifier = Modifier) {
    Text(
        text = "Dashed",
        modifier = modifier
            .dashedBorder(Color.Gray, 2.dp)
            .padding(16.dp)
    )
}

@Composable
fun Task_24(modifier: Modifier = Modifier) {
    var showMenu by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Location marker",
            modifier = Modifier
                .size(48.dp)
                .combinedClickable(
                    onClick = { selected = true },
                    onLongClick = { showMenu = true }
                )
        )
        if (selected) Text("Location selected")
        if (showMenu) Text("Directions / Remove")
    }
}

@Composable
fun Task_25(modifier: Modifier = Modifier) {
    Box(modifier = modifier.width(60.dp)) {
        Box(
            modifier = Modifier
                .requiredSize(100.dp)
                .background(Color.Green)
        )
    }
}

@Composable
fun Task_26(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(50) { index ->
            Text("Message ${index + 1}", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun Task_27(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Magenta.copy(alpha = 0.3f))
        )
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Avatar",
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
fun RowScope.Task_28() {
    Text("A", Modifier.weight(1f))
    Text("B", Modifier.weight(1f))
}

@Composable
fun Task_29(modifier: Modifier = Modifier) {
    SearchBar(modifier)
}

@Composable
fun Task_30(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Cyan)
    )
}
