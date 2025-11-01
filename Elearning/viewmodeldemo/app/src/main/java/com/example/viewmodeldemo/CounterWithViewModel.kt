import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterWithViewModel(viewModel: CounterViewModel = viewModel()) {

    val count by viewModel.count.observeAsState(0)

    Scaffold(
        topBar = { TopAppBar(title = { Text("Bộ đếm dùng ViewModel") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Giá trị hiện tại: $count",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.increaseCount() }) {
                Text("Tăng giá trị")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.resetCount() }) {
                Text("Reset giá trị")
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterWithViewModelPreview() {
    CounterWithViewModel()
}