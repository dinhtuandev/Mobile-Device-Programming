package com.example.viewmodeldemo

import CounterWithViewModel
import CounterWithoutViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleSwitcher()
        }
    }
}

@Composable
fun ExampleSwitcher() {
    var selectedExample by remember { mutableStateOf("none") }

    when (selectedExample) {
        "without" -> CounterWithoutViewModel()
        "with" -> CounterWithViewModel()
        else -> MenuScreen(onSelect = { selectedExample = it })
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(onSelect: (String) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("So sánh ViewModel vs Không ViewModel") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onSelect("without") }, modifier = Modifier.padding(8.dp)) {
                Text("🧱 Không dùng ViewModel")
            }
            Button(onClick = { onSelect("with") }, modifier = Modifier.padding(8.dp)) {
                Text("🧩 Dùng ViewModel + SavedStateHandle")
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    MainActivity()
}