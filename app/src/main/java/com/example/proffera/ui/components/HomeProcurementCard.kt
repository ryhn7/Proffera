package com.example.proffera.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proffera.R
import com.example.proffera.ui.theme.*
import java.math.BigDecimal

@Composable
fun HomeProcurement(
    projectName: String,
    winnerVendor: String,
    city: String,
    projectCost: String,
    projectDescription: String,
    projectStatus: String,
    projectDuration: String,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
//    var isBookmarked by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
//            Wrap icon and wrapping row consist of image and title
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
//                Wrap image and title in a row
                Row(
                    modifier = Modifier
                        .width(width = 285.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.dikti),
                        contentDescription = "Project Picture",
                        modifier = Modifier
                            .size(size = 50.dp)
                            .border(1.dp, DarkOrange, CircleShape)
                            .clip(CircleShape)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(width = 16.dp)
                    )
                    Column {
                        Text(
                            text = projectName,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Start,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "$winnerVendor - $city",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                            color = LightGray,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
                Icon(
                    imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = "Icon bookmark",
                    tint = if (isBookmarked) Color.Red else Color.Unspecified,
                    modifier = Modifier
                        .height(24.dp)
                        .weight(1f) // Added weight modifier
                        .wrapContentWidth(Alignment.End)
                        .clickable { // Handle bookmark click event
                            onBookmarkClick()
                        }
                )
            }
//            unknown and price
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Box(Modifier.weight(0.5f)) {
                    Text(
                        text = "Deskripsi",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
                Text(
                    text = formatProjectCost(projectCost),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
//            Description
            Text(
                text = projectDescription,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .width(width = 300.dp)
            )
//            footer card
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(Modifier.weight(1f)) {
                    Card(
                        modifier = Modifier
                            .width(100.dp)
                            .height(height = 40.dp)
                            .padding(top = 8.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = when (projectStatus) {
                            "Tidak Aktif" -> CardDefaults.cardColors(containerColor = Gray)
                            "Batal" -> CardDefaults.cardColors(containerColor = Red)
                            "Dalam Review" -> CardDefaults.cardColors(containerColor = Gold)
                            "Dalam Proses" -> CardDefaults.cardColors(containerColor = Blue)
                            "Selesai" -> CardDefaults.cardColors(containerColor = Green)
                            else -> CardDefaults.cardColors(containerColor = Gray)
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = projectStatus,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 12.sp
                                ),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "deadline",
                        modifier = Modifier
                            .height(24.dp)
                    )
                    Text(
                        text = projectDuration,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                }
            }
        }
    }
}

fun formatProjectCost(cost: String): String {
    val costValue = cost.toBigDecimalOrNull() ?: BigDecimal.ZERO

    return when {
        costValue > BigDecimal("10000000000") -> {
            val billionValue = costValue.divide(BigDecimal("1000000000"))
            "Rp.${billionValue}m"
        }
        costValue >= BigDecimal("1000000000") -> {
            val billionValue = costValue.divide(BigDecimal("1000000000"))
            "Rp.${billionValue}m"
        }
        costValue >= BigDecimal("100000000") -> {
            val millionValue = costValue.divide(BigDecimal("1000000"))
            "Rp.${millionValue}jt"
        }
        costValue >= BigDecimal("10000000") -> {
            val millionValue = costValue.divide(BigDecimal("1000000"))
            "Rp.${millionValue}jt"
        }
        else -> "Rp.$costValue"
    }
}

//@Composable
//@Preview(showBackground = true, device = Devices.PIXEL_4)
//fun HomeProcurementPreview() {
//    ProfferaTheme {
//        Surface(color = WhiteSmoke) {
//            HomeProcurement(
//                procurement = Procurement(
//                    pictureProject = R.drawable.dikti,
//                    instansiName = "Kemristekdikti",
//                    projectName = "Pembangunan Technopark",
//                    city = "Jakarta",
//                    costEstimate = "Rp.500jt",
//                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. t enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//                    status = "Dalam Proses",
//                    deadlineEstimate = "6 Bulan",
//                ),
//                modifier = Modifier.padding(16.dp)
//            )
//        }
//    }
//}