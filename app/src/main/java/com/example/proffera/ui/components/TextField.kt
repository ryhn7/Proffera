package com.example.proffera.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proffera.ui.theme.ProfferaTheme
import com.example.proffera.ui.theme.White


@Composable
fun ProfferaTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    colors: TextFieldColors = TextFieldDefaults.colors(
        cursorColor = Color.Black,
        disabledLabelColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    ),
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
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.background(White).padding(16.dp)) {
                leadingIcon?.invoke()
                Spacer(Modifier.width(16.dp))
                placeholder.invoke()
                innerTextField()
            }
        },
        maxLines = maxLines,
        minLines = minLines,
        enabled = enabled,
        keyboardActions = keyboardActions,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
    )


}

@Preview(showBackground = true)
@Composable
fun TextFieldPreviews() {
    ProfferaTheme() {
        var text by remember {
            mutableStateOf("")
        }
        ProfferaTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {},
            label = {},
            isError = true,
        )
    }
}