package com.example.proffera.ui.components


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proffera.ui.theme.DarkOrange
import com.example.proffera.ui.theme.ProfferaTheme
import com.example.proffera.ui.theme.White
import com.example.proffera.ui.theme.WhiteSmoke

@Composable
fun ApplyButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = DarkOrange),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .width(264.dp)
            .height(52.dp)
            .shadow(
                4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = DarkOrange.copy(alpha = 0.25f)
            )

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = White,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ApplyButtonPreview() {
    ProfferaTheme() {
        Surface(color = WhiteSmoke) {
            ApplyButton(
                text = "Gabung Proyek",
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
