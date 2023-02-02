package com.example.pixa

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImage(
        @Query("q") keyWord : String,
        @Query("key") key : String = "33160454-b52a139941d6e95924e40889a",
        @Query("per_page") per_page : Int = 3,
        @Query("page") page : Int = 1
    ):Call<PixaModel>

}