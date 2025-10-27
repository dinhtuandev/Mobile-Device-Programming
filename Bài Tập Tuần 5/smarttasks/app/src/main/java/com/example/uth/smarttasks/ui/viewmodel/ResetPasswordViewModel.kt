package com.example.uth.smarttasks.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class ResetPasswordViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _code = MutableStateFlow("")
    val code = _code.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    fun setEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun setCode(newCode: String) {
        _code.value = newCode
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun setConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    fun isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()

    fun isCodeValid(): Boolean = _code.value.length == 4 && _code.value.all { it.isDigit() }

    fun isPasswordValid(): Boolean = _password.value.length >= 8

    fun isConfirmPasswordValid(): Boolean = _password.value == _confirmPassword.value

    fun isFormValidForForgot(): Boolean = isEmailValid()

    fun isFormValidForVerification(): Boolean = isCodeValid()

    fun isFormValidForReset(): Boolean = isPasswordValid() && isConfirmPasswordValid()

    fun isFormValidForConfirm(): Boolean = isEmailValid() && _password.value.isNotEmpty()
}