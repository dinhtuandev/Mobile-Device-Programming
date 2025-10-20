package com.example.uth.smarttasks.ui.screens

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.uth.smarttasks.ui.viewmodel.ResetPasswordViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import com.uth.smarttasks.ui.theme.SmartTasksTheme

@Composable
fun VerificationScreen(
    viewModel: ResetPasswordViewModel,
    onNext: () -> Unit
) {
    val code by viewModel.code.collectAsState()
    val email by viewModel.email.collectAsState()
    val isValid by remember { derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Verify Code",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Enter the code we just sent on your registered Email",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = code,
            onValueChange = viewModel::setCode,
            label = { Text("Code") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = !viewModel.isCodeValid(),
            modifier = Modifier.fillMaxWidth()
        )
        if (!viewModel.isCodeValid() && code.isNotEmpty()) {
            Text(
                text = if (code.length != 4) "Code must be 4 digits" else "Code must be numeric",
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

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    SmartTasksTheme {
        VerificationScreen(viewModel = remember { ResetPasswordViewModel() }, onNext = {})
    }
}