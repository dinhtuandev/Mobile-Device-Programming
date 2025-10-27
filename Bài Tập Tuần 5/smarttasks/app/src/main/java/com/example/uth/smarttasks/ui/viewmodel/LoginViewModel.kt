package com.example.uth.smarttasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var loginError by mutableStateOf<String?>(null)

    fun onEmailChange(newEmail: String) {
        email = newEmail
        loginError = null
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        loginError = null
    }

    fun login(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(1500) // Simulate network call
            if (email == "test@example.com" && password == "password") {
                onLoginSuccess()
            } else {
                loginError = "Invalid email or password."
            }
            isLoading = false
        }
    }
}
