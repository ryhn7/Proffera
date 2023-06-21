package com.example.proffera.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Search(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchPerform: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchBar(
        query = searchQuery,
        onQueryChange = { newQuery ->
            onSearchQueryChanged(newQuery)
        },
        onSearch = {
            onSearchPerform()
            keyboardController?.hide() // Hide the software keyboard after performing the search
        },
        active = false, // The active state can be handled externally
        onActiveChange = {}, // The active state change can be handled externally
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Cari Proyek")
        },
        shape = MaterialTheme.shapes.large,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ){

    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchPreview() {
//    ProfferaTheme {
//        Surface(color = WhiteSmoke) {
//            Search()
//        }
//    }
//}