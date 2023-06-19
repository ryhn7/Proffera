package com.example.proffera.data

import com.example.proffera.data.local.AuthPreferences
import com.example.proffera.data.remote.response.LoginResponse
import com.example.proffera.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepo @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService
) {

    suspend fun loginWithEmailPassword(email: String, password:String): Flow<Result<LoginResponse>> = flow {
        try {
            val response = apiService.loginWithEmailPassword(email, password)
            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun saveAuthToken(token: String) {
        authPreferences.saveAuthToken(token)
    }

    fun getAuthToken(): Flow<String?> {
        return authPreferences.getAuthToken()
    }

    suspend fun clearAuthToken() {
        authPreferences.clearAuthToken()
    }

}