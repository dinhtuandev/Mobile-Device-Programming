package com.example.uth.smarttasks.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseAuth
import com.example.uth.smarttasks.ui.screens.*
import com.example.uth.smarttasks.ui.viewmodel.LoginViewModel
import com.example.uth.smarttasks.ui.viewmodel.ProfileViewModel
import com.example.uth.smarttasks.ui.viewmodel.ResetPasswordViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val startDestination = if (auth.currentUser != null) "home" else "welcome"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("welcome") {
            WelcomeScreen(onNavigateToLogin = { navController.navigate("login") })
        }

        composable("login") {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onForgotPassword = {
                    navController.navigate("password_reset_flow")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("home") {
            HomeScreen(onNavigateToProfile = { navController.navigate("profile") })
        }

        composable("profile") {
            val profileViewModel: ProfileViewModel = viewModel()
            ProfileScreen(
                viewModel = profileViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        navigation(startDestination = "forgot_password", route = "password_reset_flow") {
            composable("forgot_password") {
                val backStackEntry = remember(it) { navController.getBackStackEntry("password_reset_flow") }
                val resetPasswordViewModel: ResetPasswordViewModel = viewModel(backStackEntry)
                ForgotPasswordScreen(viewModel = resetPasswordViewModel, onNext = { navController.navigate("verification") }, onBack = { navController.popBackStack() })
            }
            composable("verification") {
                val backStackEntry = remember(it) { navController.getBackStackEntry("password_reset_flow") }
                val resetPasswordViewModel: ResetPasswordViewModel = viewModel(backStackEntry)
                VerificationScreen(viewModel = resetPasswordViewModel, onNext = { navController.navigate("reset_password") }, onBack = { navController.popBackStack() })
            }
            composable("reset_password") {
                val backStackEntry = remember(it) { navController.getBackStackEntry("password_reset_flow") }
                val resetPasswordViewModel: ResetPasswordViewModel = viewModel(backStackEntry)
                ResetPasswordScreen(viewModel = resetPasswordViewModel, onNext = { navController.navigate("confirm") }, onBack = { navController.popBackStack() })
            }
            composable("confirm") {
                val backStackEntry = remember(it) { navController.getBackStackEntry("password_reset_flow") }
                val resetPasswordViewModel: ResetPasswordViewModel = viewModel(backStackEntry)
                ConfirmScreen(viewModel = resetPasswordViewModel, onSubmit = {
                    navController.navigate("login") {
                        popUpTo("password_reset_flow") { inclusive = true }
                    }
                }, onBack = { navController.popBackStack() })
            }
        }
    }
}