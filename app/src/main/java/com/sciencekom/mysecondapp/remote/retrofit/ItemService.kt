package com.sciencekom.mysecondapp.remote.retrofit

import com.sciencekom.mysecondapp.InventoryResponse
import com.sciencekom.mysecondapp.remote.response.InventoryAddResponse
import com.sciencekom.mysecondapp.ui.inventory.InventoryAddActivity
import retrofit2.Call
import retrofit2.http.*

interface ItemService {

    @GET("items")
    fun getItems():Call<InventoryResponse>

    @POST("items")
    @FormUrlEncoded
    fun postItems(@Field("name") name:String,
                  @Field("category") category:String,
                  @Field("color") color:String,
                  @Field("price") price:Int
                ):Call<InventoryAddResponse>

    @PATCH("items/{id_item}")
    @FormUrlEncoded
    fun updateItems(
        @Path("id_item") id_item:String,
        @Field("name") name:String,
        @Field("category") category:String,
        @Field("color") color:String,
        @Field("price") price:Int
    ):Call<InventoryAddResponse>

    @DELETE("items/{id_item}")
    fun deleteItems(
        @Path("id_item") id_item: String
    ):Call<InventoryAddResponse>

}