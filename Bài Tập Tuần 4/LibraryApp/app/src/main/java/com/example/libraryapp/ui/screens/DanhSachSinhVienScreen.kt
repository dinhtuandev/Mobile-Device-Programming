package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.libraryapp.ui.LibraryViewModel

@Composable
fun DanhSachSinhVienScreen(viewModel: LibraryViewModel) {
    val students = viewModel.students
    var newStudents by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp)) {
        Text("👨‍🎓 Danh sách sinh viên:", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        students.forEach { Text("• $it") }
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = newStudents,
            onValueChange = { newStudents = it },
            label = { Text("tên sinh viên mới") }
        )

        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.addStudent(newStudents); newStudents = "" }) {
            Text("Thêm sách")
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("ViewModelConstructedInComposition")
fun PreviewDanhSachSinhVienScreen() {
    val vm = LibraryViewModel().apply { loadSampleData() }
    DanhSachSinhVienScreen(vm)
}
