package com.example.proffera.ui.screen.login

import android.app.Activity
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.proffera.R
import com.example.proffera.ui.components.*
import com.example.proffera.ui.theme.*


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

        LoginContent(viewModel, navController)
    }
}


@Composable
fun LoginContent(viewModel: LoginViewModel , navController: NavController) {
    val state = viewModel.state
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Text(
                    text = "Login",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ), color = Dark,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Welcome",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ), color = Dark,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))


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

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onClick = {
                        viewModel.login(navController)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                state.error?.let {
                    ErrorMessage(text = it)
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

@Composable
fun LoginContentTemp() {
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
        EmailForm2(
            value = "",
        )
        PasswordForm2(
            value = "",
            isPasswordVisible = true,
        )

    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun LoginScreenPreview() {
    ProfferaTheme() {
        Surface(color = WhiteSmoke) {
            LoginContentTemp()
        }
    }
}

