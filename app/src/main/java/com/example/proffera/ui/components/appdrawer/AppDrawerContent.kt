package com.example.proffera.ui.components.appdrawer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.proffera.R
import com.example.proffera.ui.theme.Dark
import com.example.proffera.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun <T : Enum<T>> AppDrawerContent(
    drawerState: DrawerState,
    menuItems: List<AppDrawerItemInfo<T>>,
    defaultPick: T,
    onClick: (T) -> Unit
) {
    var currentPick by remember { mutableStateOf(defaultPick) }
    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet(
        drawerContainerColor = White,
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .clip(shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)),
    ) {
        Surface(color = White) {
            Column(
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    item {
                        BuildingBlocksHeadline()
                    }
                    item {
                        HorizontalMiddleInset()
                    }
                    items(menuItems) { item ->
                        AppDrawerItem(
                            item = item,
                            isFirstItem = menuItems.first() == item,
                            isSelected = currentPick == item.drawerOption
                        ) { navOption ->

                            if (currentPick == navOption) {
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                return@AppDrawerItem
                            }

                            currentPick = navOption
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            onClick(navOption)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BuildingBlocksHeadline() {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 64.dp)
            .padding(
                start = 16.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 20.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = Dark,
            lineHeight = 1.43.em,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun HorizontalMiddleInset() {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Divider(
            color = Dark
        )
    }
}