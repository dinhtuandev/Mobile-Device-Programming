package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.libraryapp.ui.LibraryViewModel

@Composable
fun QuanLyScreen(viewModel: LibraryViewModel) {
    Column(Modifier.padding(16.dp)) {
        Text("⚙️ Màn hình Quản lý", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        Text("Số lượng sách: ${viewModel.books.size}")
        Text("Số lượng sinh viên: ${viewModel.students.size}")
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("ViewModelConstructedInComposition")
fun PreviewQuanLyScreen() {
    val vm = LibraryViewModel().apply { loadSampleData() }
    QuanLyScreen(vm)
}
