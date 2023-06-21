package com.example.proffera.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordForm(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 16.dp),
    value : String,
    onValueChange : (String) -> Unit,
    isPasswordVisible : Boolean,
    onPasswordVisibilityChange : () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
){
    Box(
        modifier = modifier
            .height(50.dp)
            .background(Color.Transparent) // Set a transparent background
            .clip(RoundedCornerShape(80.dp)) // Clip with rounded corners
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(80.dp)
            )
    ) {
        ProfferaTextField(
            modifier = Modifier.fillMaxSize(),
            value = value,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Gray
                )
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
                    Icon(imageVector = image, description, tint = Color.Gray)
                }

            },
            singleLine = true,
        )
        if (value.isEmpty()) {
            Text(
                text = "Password",
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
}