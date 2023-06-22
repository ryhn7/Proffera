package com.example.proffera.ui.screen.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffera.data.ProfferaRepo
import com.example.proffera.data.local.entity.ProcurementEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val profferaRepo: ProfferaRepo
) : ViewModel() {
    val bookmarkedProcurements: StateFlow<List<ProcurementEntity>> =
        profferaRepo.getBookmarkedProcurements()
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

}