package com.example.proffera.ui.screen.bookmarks

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proffera.ui.components.HomeProcurement
import com.example.proffera.ui.components.appbar.AppBar
import com.example.proffera.ui.screen.AboutPage
import com.example.proffera.ui.theme.WhiteSmoke

@Composable
fun BookmarksScreen(
    drawerState: DrawerState,
    bookmarksViewModel: BookmarksViewModel = hiltViewModel()
) {

    val scrollState = rememberLazyListState()

    val isScrolling = remember { mutableStateOf(false) }
    val lastOffset = remember { mutableStateOf(0) }


    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .collect { currentIndex ->
                val isScrollingUp = currentIndex < lastOffset.value
                isScrolling.value = currentIndex != lastOffset.value && !isScrollingUp
                lastOffset.value = currentIndex
            }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result if needed
    }

    val context = LocalContext.current

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
            val bookmarkedProcurements by bookmarksViewModel.bookmarkedProcurements.collectAsState()

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .background(WhiteSmoke)
                    .padding(top = 80.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                state = scrollState
            ) {
                itemsIndexed(bookmarkedProcurements) { index, procurement ->
                    // Extract the necessary data from ProcurementEntity
                    val projectName = procurement.projectName
                    val winnerVendor = procurement.winnerVendor
                    val city = procurement.city
                    val projectCost = procurement.projectCost
                    val projectDescription = procurement.projectDescription
                    val projectStatus = procurement.projectStatus
                    val projectDuration = procurement.projectDuration
                    val isBookmarked =
                        true // Assuming all bookmarked procurements are marked as true

                    // Display the bookmarked procurement item
                    HomeProcurement(
                        projectName = projectName,
                        winnerVendor = winnerVendor,
                        city = city,
                        projectCost = projectCost,
                        projectDescription = projectDescription,
                        projectStatus = projectStatus,
                        projectDuration = projectDuration,
                        isBookmarked = isBookmarked,
                        onBookmarkClick = { /*TODO*/ }
                    )
                }
            }
        }
    }
}