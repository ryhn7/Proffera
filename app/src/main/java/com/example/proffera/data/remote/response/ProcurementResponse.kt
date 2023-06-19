package com.example.proffera.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProcurementResponse(

	@field:SerializedName("message")
	val msg: String,

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class DataItem(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("id")
	val id: String
)

@Parcelize
data class Data(

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
): Parcelable
