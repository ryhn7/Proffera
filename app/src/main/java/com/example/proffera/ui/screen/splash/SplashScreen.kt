package com.example.proffera.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.proffera.NavRoutes
import com.example.proffera.R
import com.example.proffera.ui.components.navigation.MainScreen
import com.example.proffera.ui.theme.*

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.isLoggedIn.observeForever { isLoggedIn ->
            if (isLoggedIn) {
                navController.navigate(MainScreen.HomeScreen.name) {
                    // Pop up all other screens from the back stack
                    popUpTo(NavRoutes.MainRoute.name) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(NavRoutes.AuthRoute.name) {
                    popUpTo(NavRoutes.AuthRoute.name) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = OtterBrown),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(400.dp)
                .aspectRatio(1f)
                .scale(1.85f)
                .background(color = MetallicBronze, shape = CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(400.dp)
                    .aspectRatio(1f)
                    .scale(0.75f)
                    .background(color = Oil, shape = CircleShape)
            ) {

            }
        }
        Box(
            modifier = Modifier.size(300.dp),
            contentAlignment = Alignment.Center,
            content = {
                // Round logo in the center
                Image(
                    painter = painterResource(R.drawable.logo_proffera),
                    contentDescription = "Logo",
                )
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    ProfferaTheme {
        Surface(color = WhiteSmoke) {
            SplashScreen(navController = NavController(LocalContext.current))
        }
    }
}