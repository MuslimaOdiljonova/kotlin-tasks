package uz.ttpu.composearchlab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.ttpu.composearchlab.ui.taste.TastePickerRoute
import uz.ttpu.composearchlab.ui.theme.ComposeArchLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArchLabTheme {
                TastePickerRoute()
            }
        }
    }
}

@Composable
fun NameField(name: String, onNameChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = modifier
    )
}

@Composable
fun NameScreen(modifier: Modifier = Modifier) {
    // rememberSaveable saves the value in a Bundle, so it survives the activity being recreated on rotation
    var name by rememberSaveable { mutableStateOf("") }

    Column(modifier.padding(24.dp)) {
        NameField(name = name, onNameChange = { name = it })
        Text("Hello, $name!")
    }
}

@Preview(showBackground = true)
@Composable
private fun NameFieldPreview() {
    NameField(name = "Amin", onNameChange = {})
}

data class News(val title: String, val subtitle: String, val views: Int)

// Better design is Header(title, subtitle): it gets only what it shows, so it is skipped when views changes.
// Header(news) runs again every time because the whole News object is new.
@Composable
fun Header(news: News) {
    SideEffect { Log.d("Recompose", "Header(news)") }
    Column {
        Text(news.title)
        Text(news.subtitle)
    }
}

// I would group parameters into a class when there are many of them that belong together
// and the composable really uses all of them, because then the long parameter list is hard to read.
@Composable
fun Header(title: String, subtitle: String) {
    SideEffect { Log.d("Recompose", "Header(title, subtitle)") }
    Column {
        Text(title)
        Text(subtitle)
    }
}

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    var news by remember {
        mutableStateOf(News("Compose is declarative", "State in, UI out", views = 0))
    }
    Column(modifier.padding(24.dp)) {
        Header(news)
        Header(news.title, news.subtitle)
        Text("Views: ${news.views}")
        Button(onClick = { news = news.copy(views = news.views + 1) }) {
            Text("Add view")
        }
    }
}
