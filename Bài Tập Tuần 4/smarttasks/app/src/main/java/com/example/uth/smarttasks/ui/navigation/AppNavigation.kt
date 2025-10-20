package com.example.uth.smarttasks.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uth.smarttasks.ui.screens.ConfirmScreen
import com.example.uth.smarttasks.ui.screens.ForgotPasswordScreen
import com.example.uth.smarttasks.ui.screens.ResetPasswordScreen
import com.example.uth.smarttasks.ui.screens.VerificationScreen
import com.example.uth.smarttasks.ui.viewmodel.ResetPasswordViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    val viewModel: ResetPasswordViewModel = remember { ResetPasswordViewModel() }
    NavHost(navController = navController, startDestination = "forgot_password") {
        composable("forgot_password") {
            ForgotPasswordScreen(viewModel = viewModel, onNext = { navController.navigate("verification") { popUpTo("forgot_password") { inclusive = true } } })
        }
        composable("verification") {
            VerificationScreen(viewModel = viewModel, onNext = { navController.navigate("reset_password") { popUpTo("verification") { inclusive = true } } })
        }
        composable("reset_password") {
            ResetPasswordScreen(viewModel = viewModel, onNext = { navController.navigate("confirm") { popUpTo("reset_password") { inclusive = true } } })
        }
        composable("confirm") {
            ConfirmScreen(viewModel = viewModel, onSubmit = { /* Handle success */ })
        }
    }
}