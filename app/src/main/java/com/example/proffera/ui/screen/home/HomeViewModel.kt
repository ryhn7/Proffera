package com.example.proffera.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffera.data.ProfferaRepo
import com.example.proffera.data.local.entity.ProcurementEntity
import com.example.proffera.data.remote.response.DataItem
import com.example.proffera.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profferaRepo: ProfferaRepo
) : ViewModel() {
    private val _procurementsState: MutableStateFlow<UiState<List<DataItem>>> =
        MutableStateFlow(UiState.Loading)
    val procurementsState: StateFlow<UiState<List<DataItem>>> = _procurementsState

    private val _bookmarkedProcurementIds: MutableStateFlow<Set<String>> = MutableStateFlow(emptySet())
    val bookmarkedProcurementIds: StateFlow<Set<String>> = _bookmarkedProcurementIds


    fun getAllProcurements() {
        viewModelScope.launch {
            _procurementsState.value = UiState.Loading
            profferaRepo.getAllProcurements()
                .collect { result ->
                    result.onSuccess {
                        _procurementsState.value = UiState.Success(it.data)
                    }
                    result.onFailure {
                        _procurementsState.value =
                            UiState.Error(it.message ?: "An error occurred")
                    }
                }
        }
    }

    fun searchProcurements(searchQuery: String) {
        viewModelScope.launch {
            _procurementsState.value = UiState.Loading
            profferaRepo.searchProcurements(searchQuery)
                .collect { result ->
                    result.onSuccess { procurementResponse ->
                        _procurementsState.value = UiState.Success(procurementResponse.data)
                    }
                    result.onFailure {
                        _procurementsState.value = UiState.Error(it.message ?: "An error occurred")
                    }
                }
        }
    }

    fun saveBookmarked(procurement: ProcurementEntity) {
        viewModelScope.launch {
            profferaRepo.saveBookmarkedProcurement(procurement)
            _bookmarkedProcurementIds.value += procurement.id
        }
    }

    fun deleteBookmarked(procurement: ProcurementEntity) {
        viewModelScope.launch {
            profferaRepo.deleteBookmarkedProcurement(procurement)
            _bookmarkedProcurementIds.value -= procurement.id
        }
    }
}



