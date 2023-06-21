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

//    suspend fun searchProcurements(query: String): List<DataItem>? {
//        // Assuming you have access to the ApiService instance and authentication token
//        val token = authRepo.getAuthToken().firstOrNull()
//        // Retrieve all procurements from the API
//        val procurementResponse = token?.let { apiService.getAllProcurements(it) }
//
//        // Filter the procurements based on the query
//        val allProcurements = procurementResponse?.data
//        val filteredProcurements = allProcurements?.filter { dataItem ->
//            dataItem.data.namaPemenang.contains(query, ignoreCase = true) ||
//                    dataItem.data.workingAddress.contains(query, ignoreCase = true) ||
//                    dataItem.data.description.contains(query, ignoreCase = true) ||
//                    dataItem.data.kategori.contains(query, ignoreCase = true) ||
//                    dataItem.data.namaPaket.contains(query, ignoreCase = true) ||
//                    dataItem.data.governmentId.contains(query, ignoreCase = true)
//        }
//
//        return filteredProcurements
//    }

    suspend fun searchProcurements(searchQuery: String): Flow<Result<ProcurementResponse>> = flow {
        try {
            val token = authRepo.getAuthToken().firstOrNull()
            if (token == null) {
                emit(Result.failure(Exception("Token is null")))
                return@flow
            }
            val bearerToken = generateToken(token)
            val response = apiService.getAllProcurements(bearerToken)

//            filtered response based on searchQuery
            val filteredResponse = response.data.filter { dataItem ->
                dataItem.data.namaPemenang.contains(searchQuery, ignoreCase = true) ||
                        dataItem.data.workingAddress.contains(searchQuery, ignoreCase = true) ||
                        dataItem.data.description.contains(searchQuery, ignoreCase = true) ||
                        dataItem.data.kategori.contains(searchQuery, ignoreCase = true) ||
                        dataItem.data.namaPaket.contains(searchQuery, ignoreCase = true) ||
                        dataItem.data.governmentId.contains(searchQuery, ignoreCase = true)
            }
            emit(Result.success(ProcurementResponse(filteredResponse.toString(), filteredResponse)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    private fun generateToken(token: String): String {
        return "Bearer $token"
    }
}