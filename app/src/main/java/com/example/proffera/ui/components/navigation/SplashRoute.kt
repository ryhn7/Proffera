package com.example.proffera.ui.components.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.proffera.NavRoutes
import com.example.proffera.ui.screen.splash.SplashScreen

fun NavGraphBuilder.splashGraph(navController: NavController) {
    navigation(startDestination = Splash.SplashScreen.name, route = NavRoutes.SplashRoute.name) {
        composable(Splash.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }
}

enum class Splash {
    SplashScreen
}