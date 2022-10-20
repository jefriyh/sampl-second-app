package com.sciencekom.mysecondapp.remote.retrofit

import com.sciencekom.mysecondapp.InventoryResponse
import com.sciencekom.mysecondapp.remote.response.InventoryAddResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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

}