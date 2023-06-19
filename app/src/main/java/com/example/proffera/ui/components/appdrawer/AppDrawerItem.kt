package com.example.proffera.ui.components.appdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proffera.DrawerParams
import com.example.proffera.ui.components.navigation.MainScreen
import com.example.proffera.ui.theme.*

@Composable
fun <T : Any> AppDrawerItem(
    item: AppDrawerItemInfo<T>,
    isSelected: Boolean,
    isFirstItem: Boolean,
    onClick: (options: T) -> Unit
) =
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = if (isFirstItem) 16.dp else 8.dp, start = 12.dp)
            .clickable { onClick(item.drawerOption) },
        shape = RoundedCornerShape(16.dp)
    ) {
        if (isSelected) {
            StyleActiveStateEnabledShowIconTrue(item)
        } else {
            StyleInactiveStateEnabledShowIconTrue(item)
        }

    }

@Composable
fun StyleActiveStateEnabledShowIconTrue(item: AppDrawerItemInfo<*>) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .background(color = LightBlue)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 24.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
        ) {
            Icon(
                painter = painterResource(id = item.drawableId),
                contentDescription = stringResource(id = item.descriptionId),
                tint = DarkBlue,
                modifier = Modifier
                    .size(size = 24.dp)
                    .padding(top = 4.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(width = 12.dp)
            )
            Text(
                text = stringResource(id = item.title),
                color = Dark,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp,
                    letterSpacing = 0.1.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .width(width = 12.dp)
            )
        }
    }
}

@Composable
fun StyleInactiveStateEnabledShowIconTrue(item: AppDrawerItemInfo<*>) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .clip(shape = RoundedCornerShape(100.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 24.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
        ) {
            Icon(
                painter = painterResource(id = item.drawableId),
                contentDescription = stringResource(id = item.descriptionId),
                tint = DarkBlue,
                modifier = Modifier
                    .size(24.dp)
                    .padding(top = 4.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(width = 12.dp)
            )
            Text(
                text = stringResource(id = item.title),
                color = Dark,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp,
                    letterSpacing = 0.1.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

class NavigationItemProvider : PreviewParameterProvider<AppDrawerItemInfo<MainScreen>> {
    override val values = sequence {
        DrawerParams.drawerButtons.forEach { element ->
            yield(element)
        }
    }
}

@Preview
@Composable
fun NavigationItemPreview(@PreviewParameter(NavigationItemProvider::class) state: AppDrawerItemInfo<MainScreen>) {
    ProfferaTheme {
        AppDrawerItem(item = state, isSelected = false, isFirstItem = false, onClick = {})
    }
}