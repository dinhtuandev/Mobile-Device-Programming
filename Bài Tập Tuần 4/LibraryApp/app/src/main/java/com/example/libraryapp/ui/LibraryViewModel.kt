package com.example.libraryapp.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel() {
    var books by mutableStateOf(listOf("Lập trình Kotlin", "Cấu trúc dữ liệu"))
        private set

    var students by mutableStateOf(listOf("Nguyễn Văn A", "Trần Thị B"))
        private set

    fun addBook(name: String) {
        if (name.isNotBlank()) {
            books = books + name
        }
    }
    fun addStudent(name: String){
        if (name.isNotBlank()){
            students = students + name
        }
    }
    fun loadSampleData() {
        books = listOf("Kotlin cơ bản", "Jetpack Compose nâng cao")
        students = listOf("Tuấn Đẹp Trai", "Ngọc Hiền", "Minh Khang")
    }
}
