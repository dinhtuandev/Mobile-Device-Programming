package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.libraryapp.ui.LibraryViewModel

@Composable
fun DanhSachSachScreen(viewModel: LibraryViewModel) {
    val books = viewModel.books
    var newBook by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        Text("📚 Danh    sách sách:", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        books.forEach { Text("• $it") }

        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = newBook,
            onValueChange = { newBook = it },
            label = { Text("Tên sách mới") }
        )

        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.addBook(newBook); newBook = "" }) {
            Text("Thêm sách")
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("ViewModelConstructedInComposition")
fun PreviewDanhSachSachScreen() {
    val vm = LibraryViewModel().apply { loadSampleData() }
    DanhSachSachScreen(vm)
}
