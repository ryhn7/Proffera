package com.example.proffera.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailProcurementResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("data")
	val data: DataDetail
)

data class DataDetail(

	@field:SerializedName("namaPemenang")
	val namaPemenang: String,

	@field:SerializedName("workingAddress")
	val workingAddress: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("kategori")
	val kategori: String,

	@field:SerializedName("pagu")
	val pagu: Long,

	@field:SerializedName("namaPaket")
	val namaPaket: String,

	@field:SerializedName("governmentId")
	val governmentId: String
)
