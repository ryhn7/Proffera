package com.example.proffera.data.rules

fun validateEmail(email: String): ValidationResult {
    return ValidationResult(
        (!email.isNullOrEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    )
}

fun validatePassword(password: String): ValidationResult {
    return ValidationResult(
        (!password.isNullOrEmpty() && password.length >= 4)
    )
}

data class ValidationResult(
    val status: Boolean = false
)
