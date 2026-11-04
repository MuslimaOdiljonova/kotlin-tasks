package uz.ttpu.seminar_4b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.ttpu.seminar_4b.ui.theme.Seminar_4bTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Seminar_4bTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var privacyEnabled by remember { mutableStateOf(false) }
    var securityEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var dataSaverEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Profile Section
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "M",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Marko V.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "marko.design@email.com",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            // ACCOUNT SECTION
            item {
                SectionHeader(title = "ACCOUNT")
            }
            item {
                SettingRow(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    subtitle = "Manage alerts & updates",
                    endContent = {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )
                    }
                )
            }
            item {
                SettingRow(
                    icon = Icons.Default.Lock,
                    title = "Privacy",
                    subtitle = "Data, permissions, activity",
                    endContent = {
                        Switch(
                            checked = privacyEnabled,
                            onCheckedChange = { privacyEnabled = it }
                        )
                    }
                )
            }
            item {
                SettingRow(
                    icon = Icons.Default.Security,
                    title = "Security",
                    subtitle = "Password, 2-Step verification",
                    endContent = {
                        Switch(
                            checked = securityEnabled,
                            onCheckedChange = { securityEnabled = it }
                        )
                    }
                )
            }

            // PREFERENCES SECTION
            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionHeader(title = "PREFERENCES")
            }
            item {
                SettingRow(
                    icon = Icons.Default.DarkMode,
                    title = "Dark Mode",
                    subtitle = "App appearance",
                    endContent = {
                        Switch(
                            checked = darkModeEnabled,
                            onCheckedChange = { darkModeEnabled = it }
                        )
                    }
                )
            }
            item {
                SettingRow(
                    icon = Icons.Default.Language,
                    title = "Language",
                    subtitle = "English (US)",
                    endContent = {
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                )
            }
            item {
                SettingRow(
                    icon = Icons.Default.Cloud,
                    title = "Data Saver",
                    subtitle = "Reduce data usage",
                    endContent = {
                        Switch(
                            checked = dataSaverEnabled,
                            onCheckedChange = { dataSaverEnabled = it }
                        )
                    }
                )
            }

            // SUPPORT SECTION
            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionHeader(title = "SUPPORT")
            }
            item {
                SettingRow(
                    icon = Icons.Default.HeadsetMic,
                    title = "Help Center",
                    subtitle = "FAQs & contact",
                    endContent = {
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                )
            }
            item {
                SettingRow(
                    icon = Icons.Default.Info,
                    title = "About App",
                    subtitle = "Version 2.1.0",
                    endContent = {
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF1E3A8A),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SettingRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    endContent: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically, // ✅ Correct vertical alignment
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF6B46C1),
                modifier = Modifier.size(24.dp)
            )
            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
        Box(contentAlignment = Alignment.Center) {
            endContent()
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var textState by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = textState,
            onValueChange = { newText ->
                textState = newText
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            placeholder = { Text("Type to search...") },
            singleLine = true
        )

        Text(
            text = "Search products",
            modifier = Modifier.padding(top=8.dp)
        )
    }
}
