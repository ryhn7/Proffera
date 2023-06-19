package com.example.proffera.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proffera.ui.theme.DarkBlue
import com.example.proffera.ui.theme.ProfferaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfferaTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        cursorColor = Color.Black,
        disabledLabelColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    ),
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: @Composable () -> Unit = {},
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 14.sp,
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    minLines: Int = 1,
    maxLines: Int = 1,
    isError: Boolean = false,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    TextField(
        modifier = modifier,
        colors = if(enabled.not()) TextFieldDefaults.textFieldColors(
            cursorColor = DarkBlue,
            disabledLabelColor = DarkBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ) else colors,
        shape = shape,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        minLines = minLines,
        maxLines = maxLines,
        isError = isError,
        enabled = enabled,
        keyboardActions = keyboardActions,
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreviews() {
    ProfferaTheme() {
        ProfferaTextField(
            value = "Value",
            onValueChange = {},
            placeholder = {},
            label = {},
            isError = true,
        )
    }
}