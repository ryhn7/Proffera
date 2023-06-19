package com.example.proffera.data

import com.example.proffera.data.remote.response.DetailProcurementResponse
import com.example.proffera.data.remote.response.ProcurementResponse
import com.example.proffera.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfferaRepo @Inject constructor(
    private val apiService: ApiService,
    private val authRepo: AuthRepo
) {
    suspend fun getAllProcurements(

    ): Flow<Result<ProcurementResponse>> = flow {
        try {
            val token = authRepo.getAuthToken().firstOrNull()
            if (token == null) {
                emit(Result.failure(Exception("Token is null")))
                return@flow
            }
            val bearerToken = generateToken(token)
            val response = apiService.getAllProcurements(bearerToken)
            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    suspend fun getDetailProcurement(
        id: String
    ): Flow<Result<DetailProcurementResponse>> = flow {
        try {
            val token = authRepo.getAuthToken().firstOrNull()
            if (token == null) {
                emit(Result.failure(Exception("Token is null")))
                return@flow
            }
            val bearerToken = generateToken(token)
            val response = apiService.getDetailProcurement(bearerToken, id)
            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    private fun generateToken(token: String): String {
        return "Bearer $token"
    }
}