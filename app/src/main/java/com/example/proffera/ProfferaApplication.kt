package com.example.proffera

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.proffera.ui.components.appdrawer.AppDrawerContent
import com.example.proffera.ui.components.appdrawer.AppDrawerItemInfo
import com.example.proffera.ui.components.navigation.MainScreen
import com.example.proffera.ui.components.navigation.authGraph
import com.example.proffera.ui.components.navigation.mainGraph
import com.example.proffera.ui.components.navigation.splashGraph
import com.example.proffera.utils.UtilViewModel


val LocalBackPressedDispatcher =
    compositionLocalOf<OnBackPressedDispatcher> { error("No Back Dispatcher provided") }

@Composable
fun ProfferaApplication(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    viewModel: UtilViewModel = hiltViewModel(),
) {
    val activity = (LocalContext.current as? Activity)
    val isLoggedIn by viewModel.isLoggedIn.observeAsState()

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    CompositionLocalProvider(LocalBackPressedDispatcher provides backDispatcher!!) {
        BackHandler {
            if (isLoggedIn == true && navController.currentBackStackEntry?.destination?.route == MainScreen.HomeScreen.name) {
                activity?.finish()
            } else {
                Log.d(
                    ContentValues.TAG,
                    "ProfferaApplication: ${navController.currentBackStackEntry?.destination?.route}"
                )
                navController.navigateUp()
            }
        }
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawerContent(
                drawerState = drawerState,
                menuItems = DrawerParams.drawerButtons,
                defaultPick = MainScreen.HomeScreen
            ) { onUserPickedOption ->
                when (onUserPickedOption) {
                    MainScreen.HomeScreen -> {
                        Log.d(
                            ContentValues.TAG,
                            "ProfferaApplication: ${onUserPickedOption.name} Screen"
                        )
                        navController.navigate(onUserPickedOption.name) {
                            popUpTo(NavRoutes.MainRoute.name)
                        }
                    }
                    MainScreen.BookmarksScreen -> {
                        Log.d(
                            ContentValues.TAG,
                            "ProfferaApplication: ${onUserPickedOption.name} Screen"
                        )
                        navController.navigate(onUserPickedOption.name) {
                            popUpTo(NavRoutes.MainRoute.name)
                        }
                    }
                    MainScreen.Logout -> {
                        viewModel.logout()
                        navController.navigate(NavRoutes.AuthRoute.name) {
                            popUpTo(NavRoutes.AuthRoute.name) {
                                inclusive = true
                            }
                        }
                    }
                    else -> {}
                }
            }
        },
        gesturesEnabled = isLoggedIn == true
    ) {
        NavHost(
            navController,
            startDestination = NavRoutes.SplashRoute.name,
        ) {
            splashGraph(navController)
            authGraph(navController)
            mainGraph(drawerState, navController)
        }
    }
}


enum class NavRoutes {
    SplashRoute,
    AuthRoute,
    MainRoute,
}

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            MainScreen.HomeScreen,
            R.string.drawer_home,
            R.drawable.ic_home,
            R.string.drawer_home_description
        ),
        AppDrawerItemInfo(
            MainScreen.BookmarksScreen,
            R.string.drawer_bookmarks,
            R.drawable.ic_info,
            R.string.drawer_bookmarks_description
        ),
        AppDrawerItemInfo(
            MainScreen.Logout,
            R.string.drawer_logout,
            R.drawable.ic_logout,
            R.string.drawer_logout_description
        ),
    )
}