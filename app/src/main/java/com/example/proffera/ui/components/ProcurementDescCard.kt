package com.example.proffera.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proffera.R
import com.example.proffera.ui.theme.ProfferaTheme
import com.example.proffera.ui.theme.White
import com.example.proffera.ui.theme.WhiteSmoke

@Composable
fun ProcurementDescCard(
    name: String,
    organization: String,
    @DrawableRes imageIcon: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
            .width(160.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            //        Wrap image and title in a row
            Row(
                modifier = Modifier
                    .width(width = 120.dp)
            ) {
                Image(
                    painter = painterResource(imageIcon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(size = 20.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 4.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy((-6).dp)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = organization,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 10.sp),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
            Image(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = "icon",
                modifier = Modifier
                    .height(16.dp)
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProcurementDescCardPreview() {
    ProfferaTheme() {
        Surface(color = WhiteSmoke) {
            ProcurementDescCard(
                "Tender",
                "Kementrian XYZ",
                R.drawable.ic_info,
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}