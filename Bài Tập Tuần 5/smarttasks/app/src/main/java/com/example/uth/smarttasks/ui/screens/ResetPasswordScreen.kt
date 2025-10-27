package com.example.uth.smarttasks.ui.screens

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.uth.smarttasks.ui.viewmodel.ResetPasswordViewModel
import com.example.uth.smarttasks.ui.theme.SmartTasksTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    viewModel: ResetPasswordViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val email by viewModel.email.collectAsState()
    val isValid by remember { derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(email).matches() } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reset Password") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create New Password",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your new password must be different from your previously used password",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = password,
            onValueChange = viewModel::setPassword,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = !viewModel.isPasswordValid(),
            modifier = Modifier.fillMaxWidth()
        )
        if (!viewModel.isPasswordValid() && password.isNotEmpty()) {
            Text(
                text = "Password must be at least 8 characters",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = viewModel::setConfirmPassword,
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = !viewModel.isConfirmPasswordValid(),
            modifier = Modifier.fillMaxWidth()
        )
        if (!viewModel.isConfirmPasswordValid() && confirmPassword.isNotEmpty()) {
            Text(
                text = "Passwords do not match",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onNext,
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}

}

@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    SmartTasksTheme {
        ResetPasswordScreen(viewModel = remember { ResetPasswordViewModel() }, onNext = {}, onBack = {})
    }
}