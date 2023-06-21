package com.example.proffera.ui.screen.login

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.proffera.R
import com.example.proffera.ui.components.ButtonComponent
import com.example.proffera.ui.components.EmailForm
import com.example.proffera.ui.components.PasswordForm
import com.example.proffera.ui.theme.*
import kotlinx.coroutines.launch


val LocalBackPressedDispatcher =
    compositionLocalOf<OnBackPressedDispatcher> { error("No Back Dispatcher provided") }

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val activity = (LocalContext.current as? Activity)
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    CompositionLocalProvider(LocalBackPressedDispatcher provides backDispatcher!!) {
        BackHandler {
            if (!viewModel.isUserLoggedIn()) {
                activity?.finish()
            } else {
                // Navigate back
                navController.popBackStack()
            }
        }

        LoginContent(viewModel, navController, LocalContext.current)
    }
}


@Composable
fun LoginContent(viewModel: LoginViewModel , navController: NavController, context: Context = LocalContext.current) {
    val state = viewModel.state
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WhiteSmoke)
    ) {
        Card(shape = RoundedCornerShape(bottomStart = 120.dp)) {
            Box(
                modifier = Modifier
                    .height(320.dp)
                    .background(color = OtterBrown),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(400.dp)
                        .aspectRatio(1f)
                        .scale(0.80f)
                        .background(color = MetallicBronze, shape = CircleShape)
                ) {
                    Box(
                        modifier = Modifier
                            .size(400.dp)
                            .aspectRatio(1f)
                            .scale(0.83f)
                            .background(color = Oil, shape = CircleShape)
                    ) {
                    }
                }
                Box(
                    modifier = Modifier.size(200.dp),
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

        Spacer(modifier = Modifier.height(32.dp))

        EmailForm(
            value = state.email,
            onValueChange = viewModel::onEmailChange
        )

        PasswordForm(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            isPasswordVisible = state.isPasswordVisible,
            onPasswordVisibilityChange = viewModel::onPasswordVisibilityChange,
        )

        Spacer(modifier = Modifier.height(100.dp))

        ButtonComponent(
            value = stringResource(id = R.string.login),
            onClick = {
                viewModel.login(navController)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LaunchedEffect(state.error) {
            state.error?.let { errorMessage ->
                coroutineScope.launch {
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp), color = DarkBlue)
        }
    }
}

