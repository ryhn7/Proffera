package com.example.proffera.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffera.data.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UtilViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            val authToken = authRepo.getAuthToken().firstOrNull()
            val isAuthenticated = !authToken.isNullOrEmpty()
            _isLoggedIn.value = isAuthenticated
        }
    }

    fun logout() = viewModelScope.launch {
        authRepo.clearAuthToken()
        _isLoggedIn.value = false
    }
}