package com.example.uth.smarttasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uth.smarttasks.ui.theme.SmartTasksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToProfile: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                actions = {
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(Icons.Filled.Person, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome!", style = MaterialTheme.typography.headlineLarge)
            Text("You are logged in.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SmartTasksTheme {
        HomeScreen(onNavigateToProfile = {})
    }
}
