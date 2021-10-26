package com.ram.test.network



import com.ram.test.model.RecyclerViewData
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

    @GET("recipes.json")
    fun getDataFromApi(): Call<List<RecyclerViewData>>
}