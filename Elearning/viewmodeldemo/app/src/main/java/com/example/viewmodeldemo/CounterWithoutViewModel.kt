import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterWithoutViewModel() {


    var count by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Bộ đếm không dùng ViewModel") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Giá trị hiện tại: $count",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { count++ }) {
                Text("Tăng giá trị")
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterWithoutViewModelPreview() {
    CounterWithoutViewModel()
}
