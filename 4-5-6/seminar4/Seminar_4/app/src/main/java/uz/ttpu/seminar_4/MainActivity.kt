package uz.ttpu.seminar_4

import android.os.Build
import android.os.Bundle
import android.os.Trace.isEnabled
import android.widget.Button
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.ttpu.seminar_4.ui.theme.Seminar_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Seminar_4Theme {
                Task_15("Muslima")
            }
        }
    }
}

@Composable
fun Task_1(modifier: Modifier = Modifier) {
    Text(
        text = "Hello",
        modifier = Modifier
            .background(Color.Yellow)
            .padding(16.dp)
    )
}
@Composable
fun Task_2(modifier: Modifier = Modifier) {
    Text(
        text = "Hello",
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Yellow)
    )
}

@Composable
fun Task_3(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .clickable { onIconClick() }
            .padding(12.dp)
            .size(24.dp)
    )
}

@Composable
fun Task_4(modifier: Modifier = Modifier) {
    Button(
        modifier = Modifier.fillMaxSize(),
        onClick = { onIconClick() }
    ) {
        Text("Continue")
    }
}

@Composable
fun Task_5(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Green)

    )
}

@Composable
fun Task_6(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .background(Color.Yellow)
    ) {
        Text(text="Hello")
    }
}

@Composable
fun Task_7(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(
                color = Color(0xFFEADDFF),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text("Hello World!")
    }
}

@Composable
fun Task_8(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color(0xFFEADDFF),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    )
    {}
}

@Composable
fun Task_9(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .offset(4.dp, 2.dp)
            .background(Color.Yellow)
    ) {
        Text("This is the tooltip content")
    }
}

@Composable
fun Task_10(modifier: Modifier = Modifier) {
    val isEnabled = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Enable notifications",
            modifier = Modifier.weight(2f)
        )
        Switch(
            checked = isEnabled.value,
            onCheckedChange = { isEnabled.value = it },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun Task_11(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Task_12(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Text(text = name.first().toString(), modifier = modifier.align(Alignment.Center))
    }
}

@Composable
fun Task_13(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Loading...",
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@Composable
fun Task_14(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.img),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(16f / 9f),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun Task_15(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .size(56.dp)
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(56.dp),
                color = MaterialTheme.colorScheme.primary)
            .padding(2.dp)
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,

    ) {
        Text(
            text = name.first().toString(),
            modifier = Modifier
                .align(Alignment.Center))

    }
}



fun onIconClick() {
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Seminar_4Theme {
        Task_1()
    }
}