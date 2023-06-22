package com.example.proffera.ui.screen.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proffera.R
import com.example.proffera.data.local.entity.ProcurementEntity
import com.example.proffera.data.remote.response.DataItem
import com.example.proffera.ui.common.UiState
import com.example.proffera.ui.components.HomeProcurement
import com.example.proffera.ui.components.Search
import com.example.proffera.ui.components.appbar.AppBar
import com.example.proffera.ui.screen.AboutPage
import com.example.proffera.ui.theme.WhiteSmoke

@Composable
fun HomeScreen(
    drawerState: DrawerState,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val scrollState = rememberLazyListState()

    val isScrolling = remember { mutableStateOf(false) }
    val lastOffset = remember { mutableStateOf(0) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result if needed
    }

    val context = LocalContext.current

    val searchQuery = remember { mutableStateOf("") }

    fun searchProcurements() {
        viewModel.searchProcurements(searchQuery.value)
    }


    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .collect { currentIndex ->
                val isScrollingUp = currentIndex < lastOffset.value
                isScrolling.value = currentIndex != lastOffset.value && !isScrollingUp
                lastOffset.value = currentIndex
            }
    }

    Scaffold(
        topBar = {
            if (!isScrolling.value) {
                AppBar(
                    drawerState = drawerState,
                    onProfileClick = {
                        val intent = Intent(context, AboutPage::class.java)
                        launcher.launch(intent)
                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(WhiteSmoke)
        ) {
            viewModel.procurementsState.collectAsState().value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getAllProcurements()
                        viewModel.searchProcurements(searchQuery.value)
                    }
                    is UiState.Success -> {
                        HomeScreenContent(
                            listProcurement = uiState.data,
                            scrollState = scrollState,
                            onClickDetail = navigateToDetail,
                            searchQuery = searchQuery.value,
                            onSearchQueryChanged = { newQuery -> searchQuery.value = newQuery },
                            onSearchPerform = { searchProcurements() },
                        )
                    }
                    is UiState.Error -> {
                        Log.d(TAG, "HomeScreen: Error")
                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreenContent(
    listProcurement: List<DataItem>,
    scrollState: LazyListState,
    onClickDetail: (String) -> Unit = {},
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchPerform: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(WhiteSmoke)
            .padding(16.dp),
        state = scrollState
    ) {
        item {
            Text(
                text = stringResource(id = R.string.header_wording),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .width(230.dp)
                    .padding(top = 56.dp)
            )
            Search(
                searchQuery = searchQuery,
                onSearchQueryChanged = onSearchQueryChanged,
                onSearchPerform = onSearchPerform,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }
        items(listProcurement) { procurement ->
            val isProcurementBookmarked = viewModel.bookmarkedProcurementIds.collectAsState().value.contains(procurement.id)
            HomeProcurement(
                projectName = procurement.data.namaPaket,
                winnerVendor = procurement.data.namaPemenang,
                city = procurement.data.workingAddress,
                projectCost = procurement.data.pagu.toString(),
                projectDescription = procurement.data.description ?: "",
                projectStatus = "Dalam Review",
                projectDuration = "6 Bulan",
                isBookmarked = isProcurementBookmarked,
                onBookmarkClick = {
                    val procurementEntity = convertToProcurementEntity(procurement)
                    if (isProcurementBookmarked) {
                        viewModel.deleteBookmarked(procurementEntity)
                    } else {
                        viewModel.saveBookmarked(procurementEntity)
                    }
                },
                modifier = Modifier
                    .clickable {
                        Log.d(TAG, "HomeScreenContent: ${procurement.id}")
                        onClickDetail(procurement.id)
                    }
                    .padding(bottom = 16.dp)
            )
        }
    }
}

fun convertToProcurementEntity(dataItem: DataItem): ProcurementEntity {
    val id = dataItem.id
    val projectName = dataItem.data.namaPaket
    val winnerVendor = dataItem.data.namaPemenang
    val city = dataItem.data.workingAddress
    val projectCost = dataItem.data.pagu.toString()
    val projectDescription = dataItem.data.description ?: ""
    val projectStatus = "Dalam Review"
    val projectDuration = "6 Bulan"

    return ProcurementEntity(
        id = id,
        projectName = projectName,
        winnerVendor = winnerVendor,
        city = city,
        projectCost = projectCost,
        projectDescription = projectDescription,
        projectStatus = projectStatus,
        projectDuration = projectDuration
    )
}


//@Composable
//@Preview(showBackground = true, device = Devices.PIXEL_4)
//fun HomeScreenPreview() {
//    ProfferaTheme() {
//        Surface(color = WhiteSmoke) {
//            HomeScreen(drawerState = rememberDrawerState(DrawerValue.Closed))
//        }
//
//    }
//}