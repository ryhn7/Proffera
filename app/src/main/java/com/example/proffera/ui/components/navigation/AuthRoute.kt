package com.example.proffera.ui.components.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.proffera.NavRoutes
import com.example.proffera.ui.screen.login.LoginScreen

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = AuthScreen.LoginScreen.name, route = NavRoutes.AuthRoute.name) {
        composable(AuthScreen.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
    }
}

enum class AuthScreen {
    LoginScreen,
}