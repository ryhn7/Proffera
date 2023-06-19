package com.example.proffera.ui.components.navigation

sealed class Screen(val route: String) {
    object DetailScreen: Screen("detail/{projectId}") {
        fun createRoute(projectId: String): String {
            return "detail/$projectId"
        }
    }
}
