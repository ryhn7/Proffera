package com.example.proffera.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proffera.R
import com.example.proffera.ui.theme.DarkOrange
import com.example.proffera.ui.theme.ProfferaTheme
import com.example.proffera.ui.theme.White
import com.example.proffera.ui.theme.WhiteSmoke

@Composable
fun DownloadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .size(45.dp)
            .border(1.dp, DarkOrange, CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_download),
            contentDescription = "download btn",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DownloadButtonPreview() {
    ProfferaTheme() {
        Surface(color = WhiteSmoke) {
            DownloadButton(
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}