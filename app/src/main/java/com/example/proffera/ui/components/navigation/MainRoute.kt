package com.example.proffera.ui.components.navigation

import androidx.compose.material3.DrawerState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.proffera.NavRoutes
import com.example.proffera.ui.screen.bookmarks.BookmarksScreen
import com.example.proffera.ui.screen.detail.DetailScreen
import com.example.proffera.ui.screen.home.HomeScreen

fun NavGraphBuilder.mainGraph(drawerState: DrawerState, navController: NavController) {
    navigation(startDestination = MainScreen.HomeScreen.name, route = NavRoutes.MainRoute.name) {
        composable(MainScreen.HomeScreen.name) {
            HomeScreen(drawerState, navigateToDetail = { projectId ->
                navController.navigate(Screen.DetailScreen.createRoute(projectId))
            })
        }
        composable(MainScreen.BookmarksScreen.name) {
            BookmarksScreen(drawerState, navigateToDetail = { projectId ->
                navController.navigate(Screen.DetailScreen.createRoute(projectId))
            })
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("projectId") { type = NavType.StringType }),
        ) {
            val id = it.arguments?.getString("projectId") ?: ""
            DetailScreen(
                projectId = id,
                navigateBack = {
                    navController.navigateUp()
                },
                onApplyClick = {
                },
                onDownloadClick = {

                }
            )

        }
        composable(MainScreen.Logout.name) {

        }
    }
}

enum class MainScreen {
    HomeScreen,
    ProfileScreen,
    HistoryScreen,
    BookmarksScreen,
    RegisterScreen,
    ProcurementScreen,
    ApplyScreen,
    Logout,
}