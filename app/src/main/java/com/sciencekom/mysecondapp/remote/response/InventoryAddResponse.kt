package com.sciencekom.mysecondapp.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InventoryAddResponse(

	@field:SerializedName("data")
	val data: DataInventoryAdd,

	@field:SerializedName("meta")
	val meta: Meta
) : Parcelable

@Parcelize
data class Meta(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class DataInventoryAdd(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("color")
	val color: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id_item")
	val idItem: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("category")
	val category: String
) : Parcelable
