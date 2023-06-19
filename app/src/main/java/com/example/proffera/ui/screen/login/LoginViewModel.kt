package com.example.proffera.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.proffera.NavRoutes
import com.example.proffera.data.AuthRepo
import com.example.proffera.data.rules.validateEmail
import com.example.proffera.data.rules.validatePassword
import com.example.proffera.ui.components.navigation.MainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    var isLoggedIn by mutableStateOf(false)

    fun onEmailChange(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state = state.copy(password = password)
    }

    fun onPasswordVisibilityChange() {
        state = state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    fun login(navController: NavController) = viewModelScope.launch {
        state = state.copy(error = null)
        val email = state.email.trim()
        val password = state.password.trim()

        val emailValidation = validateEmail(email)
        val passwordValidation = validatePassword(password)

        if (!emailValidation.status) {
            state = state.copy(error = "Invalid email address")
            return@launch
        }

        if (!passwordValidation.status) {
            state = state.copy(error = "Invalid password (minimum 8 characters required)")
            return@launch
        }

        if (email.isEmpty() || password.isEmpty()) {
            state = state.copy(error = "Email and password must not be empty")
            return@launch
        }

        state = state.copy(isLoading = true)
        authRepo.loginWithEmailPassword(email, password)
            .collect { result ->
                state = state.copy(isLoading = false) // Clear loading state

                result.onSuccess { loginResponse ->
                    val authToken = loginResponse.token
                    authRepo.saveAuthToken(authToken)

                    isLoggedIn = true

                    navController.navigate(MainScreen.HomeScreen.name) {
                        // Pop up all other screens from the back stack
                        popUpTo(NavRoutes.MainRoute.name) {
                            inclusive = true
                        }
                    }
                }.onFailure { exception ->
                    state = state.copy(error = "Login failed: ${exception.message}")
                }
            }
    }

    fun isUserLoggedIn(): Boolean {
        return isLoggedIn
    }


    fun setErrorMessage(message: String) {
        state = state.copy(error = message)
    }
}