package com.example.uth.smarttasks.ui.screens

import com.example.uth.smarttasks.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uth.smarttasks.WelcomeActivity
import com.example.uth.smarttasks.ui.theme.SmartTasksTheme

@Composable
fun WelcomeScreen(onNavigateToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to SmartTasks",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Placeholder for the app logo
        Box(
            modifier = Modifier
                .size(150.dp)
        ) {
            Box(
                modifier = Modifier.size(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App logo",
                    modifier = Modifier.fillMaxSize()
                )
                Spacer(modifier = Modifier.height(20.dp))
            }


        }
        Text(
            text = "SmartTask",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = onNavigateToLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    SmartTasksTheme {
    WelcomeScreen(onNavigateToLogin = {})
    }
}
