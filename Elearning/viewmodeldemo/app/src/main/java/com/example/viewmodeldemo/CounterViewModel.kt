import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CounterViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    companion object {
        private const val KEY_COUNT = "count"
    }

    val count = savedStateHandle.getLiveData(KEY_COUNT, 0)

    fun increaseCount() {
        val current = savedStateHandle.get<Int>(KEY_COUNT) ?: 0
        savedStateHandle.set(KEY_COUNT, current + 1)
    }
    fun resetCount() {
        savedStateHandle.set(KEY_COUNT, 0)
    }

}
@Preview(showBackground = true)
@Composable
fun CounterViewModelPreview() {
    // Mock UI cho preview
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Giá trị hiện tại: 0",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { }) {
            Text("Tăng giá trị")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { }) {
            Text("Reset giá trị")
        }
    }
}