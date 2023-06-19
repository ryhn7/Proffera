package com.example.proffera.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffera.data.ProfferaRepo
import com.example.proffera.data.remote.response.DetailProcurementResponse
import com.example.proffera.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val profferaRepo: ProfferaRepo
) : ViewModel() {
    private val _detailProcurementsState: MutableStateFlow<UiState<DetailProcurementResponse>> =
        MutableStateFlow(UiState.Loading)
    val detailProcurementsState: StateFlow<UiState<DetailProcurementResponse>> =
        _detailProcurementsState


    fun getDetailProcurement(id: String) {
        viewModelScope.launch {
            _detailProcurementsState.value = UiState.Loading
            profferaRepo.getDetailProcurement(id)
                .collect { result ->
                    result.onSuccess {
                        _detailProcurementsState.value = UiState.Success(it)
                    }
                    result.onFailure {
                        _detailProcurementsState.value =
                            UiState.Error(it.message ?: "An error occurred")
                    }
                }
        }
    }
}