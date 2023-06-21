package com.example.proffera.ui.screen.detail

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proffera.R
import com.example.proffera.ui.common.UiState
import com.example.proffera.ui.components.ApplyButton
import com.example.proffera.ui.components.DownloadButton
import com.example.proffera.ui.components.ProcurementDescCard
import com.example.proffera.ui.theme.*

@Composable
fun DetailScreen(
    projectId: String,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    onApplyClick: () -> Unit,
    onDownloadClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteSmoke)
    ) {
        viewModel.detailProcurementsState.collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getDetailProcurement(projectId)
                }
                is UiState.Success -> {
                    val data = uiState.data
                    DetailContent(
                        data.data.namaPemenang,
                        data.data.namaPaket,
                        R.drawable.dikti,
                        "Kota XYZ",
                        data.data.description,
                        "01.692.131.4-073.000",
                        data.data.pagu.toString(),
                        "Juli 2023 - Desember 2023",
                        "Tinggi",
                        "Dalam Review",
                        onBackClick = navigateBack,
                        onApplyClick = onApplyClick,
                        onDownloadClick = onDownloadClick,
                    )
                }
                is UiState.Error -> {
                    Log.d(ContentValues.TAG, "DetailScreen: Error")
                }
            }
        }
    }

}

@Composable
fun DetailContent(
    agencyName: String,
    projectName: String,
    @DrawableRes projectImage: Int,
    projectLocation: String,
    projectDescription: String,
    projectNPWP: String,
    projectContractPrice: String,
    projectExecutionTime: String,
    projectRisk: String,
    projectStatus: String,
    onBackClick: () -> Unit,
    onApplyClick: () -> Unit,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = DarkBlue,
                ),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp, start = 8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Button",
                            tint = White
                        )
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = WhiteSmoke,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(top = 130.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = projectName,
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 25.dp)
                        )
                        Text(
                            text = "$agencyName - $projectLocation",
                            style = MaterialTheme.typography.titleSmall,
                            color = LightGray,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ProcurementDescCard(
                            name = "Vendor",
                            organization = agencyName,
                            imageIcon = R.drawable.ic_enterprise,
                            modifier = Modifier.padding(start = 25.dp)
                        ) {

                        }
                        ProcurementDescCard(
                            name = "Tender",
                            organization = "Kemenristekdikti",
                            imageIcon = R.drawable.ic_info,
                            modifier = Modifier.padding(end = 25.dp)
                        ) {

                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp),
                ) {
                    Column {
                        Text(
                            text = "Deskripsi Proyek",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(start = 25.dp)
                        )
                        Text(
                            text = projectDescription,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                            modifier = Modifier.padding(horizontal = 25.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                ) {
                    Column {
                        Text(
                            text = "Informasi Proyek",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(start = 25.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Pemenang:",
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(start = 25.dp, end = 8.dp)
                            )
                            Text(
                                text = agencyName,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(end = 25.dp),
                                color = DarkOrange
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "No. NPWP :",
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(start = 25.dp, end = 8.dp)
                            )
                            Text(
                                text = projectNPWP,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(end = 25.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Nilai Kontrak :",
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(start = 25.dp, end = 8.dp)
                            )
                            Text(
                                text = "Rp. $projectContractPrice",
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(end = 25.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Risiko :",
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(start = 25.dp, end = 8.dp)
                            )
                            Text(
                                text = projectRisk,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(end = 25.dp),
                                color = Red
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Status :",
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(start = 25.dp, end = 8.dp)
                            )
                            Text(
                                text = projectStatus,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(end = 25.dp),
                                color = Gold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Waktu Pelaksanaan :",
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(start = 25.dp, end = 8.dp)
                            )
                            Text(
                                text = projectExecutionTime,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                modifier = Modifier.padding(end = 25.dp)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp, bottom = 16.dp),
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth(), horizontalArrangement = Arrangement.Start
                    ) {
                        DownloadButton(
                            onClick = {
                                onDownloadClick()
                                showToast(context, "Download juknis berhasil")
                            },
                            modifier = Modifier.padding(start = 25.dp, top = 4.dp)
                        )
                        ApplyButton(
                            text = "Daftar",
                            onClick = {
                                onApplyClick()
                                showToast(context, "Daftar proyek berhasil")
                            },
                            modifier = Modifier.padding(start = 32.dp, end = 25.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 80.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    shadowElevation = 16.dp,
                    color = DarkBlue,
                    modifier = Modifier.size(100.dp)
                ) {
                    Image(
                        painter = painterResource(projectImage),
                        contentDescription = "Project Image",
                        modifier = Modifier
                            .size(size = 100.dp)
                            .border(1.dp, DarkOrange, CircleShape)
                            .clip(CircleShape)
                            .shadow(
                                elevation = 8.dp,
                                shape = CircleShape,
                                clip = true
                            )
                    )
                }
            }
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun DetailScreenPreview() {
    ProfferaTheme() {
        Surface(color = WhiteSmoke) {
            DetailContent(
                "Kemenristekdikti",
                "Pembangunan Technopark",
                R.drawable.dikti,
                "Jakarta",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. t enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "01.692.131.4-073.000",
                "Rp.500jt",
                "Juli 2023 - Desember 2023",
                "Tinggi",
                "Dalam Proses",
                onBackClick = { },
                onApplyClick = { },
                onDownloadClick = { }
            )
        }

    }
}
