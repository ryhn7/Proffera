package com.example.proffera.ui.screen.login

data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSubmitting: Boolean = false,

    var emailError :Boolean = false,
    var passwordError : Boolean = false
)

