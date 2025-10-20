package com.example.libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.libraryapp.ui.*
import com.example.libraryapp.ui.screens.QuanLyScreen
import com.example.libraryapp.ui.screens.DanhSachSachScreen
import com.example.libraryapp.ui.screens.DanhSachSinhVienScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryApp()
        }
    }
}

@Composable
fun LibraryApp() {
    val navController = rememberNavController()
    val vm: LibraryViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "sach",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("sach") { DanhSachSachScreen(vm) }
            composable("sinhvien") { DanhSachSinhVienScreen(vm) }
            composable("quanly") { QuanLyScreen(vm) }
        }
    }
}
