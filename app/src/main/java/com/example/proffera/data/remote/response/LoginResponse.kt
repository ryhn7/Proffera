package com.example.proffera.data.remote.response

import com.google.gson.annotations.SerializedName

 data class LoginResponse(

	@field:SerializedName("token")
	val token: String
)
