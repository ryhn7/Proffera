package com.example.proffera.data.remote.retrofit

import com.example.proffera.data.remote.response.DetailProcurementResponse
import com.example.proffera.data.remote.response.LoginResponse
import com.example.proffera.data.remote.response.ProcurementResponse
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginWithEmailPassword(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("procurement")
    suspend fun getAllProcurements(
        @Header("Authorization") token: String,
    ): ProcurementResponse

    @GET("procurement/{id}")
    suspend fun getDetailProcurement(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DetailProcurementResponse
}