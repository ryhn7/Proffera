package com.example.proffera.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proffera.ui.theme.ProfferaTheme

@Composable
fun PasswordForm(
    modifier : Modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
    value : String,
    onValueChange : (String) -> Unit,
    isPasswordVisible : Boolean,
    onPasswordVisibilityChange : () -> Unit,
    labelText : String = "Password",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
){
    ProfferaTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        },
        label = {
            Text(labelText)
        },
        placeholder = {
            Text(labelText)
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            val image = if (isPasswordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description =
                if (isPasswordVisible) "Hide password" else "Show password"

            IconButton(onClick = onPasswordVisibilityChange) {
                Icon(imageVector = image, description)
            }
        },
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordPreviews() {
    ProfferaTheme() {
        PasswordForm(
            value = "",
            onValueChange = {},
            isPasswordVisible = true,
            onPasswordVisibilityChange = {},
        )
    }
}