package com.sciencekom.mysecondapp.remote.retrofit

import com.sciencekom.mysecondapp.InventoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface ItemService {

    @GET("items")
    fun getItems():Call<InventoryResponse>

}