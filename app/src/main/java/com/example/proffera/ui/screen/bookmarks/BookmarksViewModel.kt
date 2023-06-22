package com.example.proffera.ui.screen.bookmarks

import androidx.lifecycle.ViewModel
import com.example.proffera.data.ProfferaRepo
import com.example.proffera.data.remote.response.DataItem
import com.example.proffera.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val profferaRepo: ProfferaRepo
) : ViewModel() {
    private val _bookmarkedProcurement: MutableStateFlow<UiState<List<DataItem>>> =
        MutableStateFlow(UiState.Loading)
    val bookmarkedProcurement: MutableStateFlow<UiState<List<DataItem>>> = _bookmarkedProcurement
}