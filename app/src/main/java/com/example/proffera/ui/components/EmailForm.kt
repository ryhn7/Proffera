package com.example.proffera.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proffera.ui.theme.ProfferaTheme

@Composable
fun EmailForm(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
    value: String,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector = Icons.Filled.Email,
    label: String = "Email",
    placeholder: String = "Email",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next
    ),
) {
    ProfferaTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
        },
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholder)
        },
        singleLine = true,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun EmailForm2(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 16.dp),
    value: String,
    imageVector: ImageVector = Icons.Filled.Email,
    label: String = "Email",
    placeholder: String = "Email",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next
    ),
) {
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
            modifier = Modifier.fillMaxSize(), // Take the available space inside the box
            value = value,
            leadingIcon = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            },
            singleLine = true,
            keyboardOptions = keyboardOptions
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmailPreviews() {
    ProfferaTheme {
        EmailForm2(value = "Email")
    }
}