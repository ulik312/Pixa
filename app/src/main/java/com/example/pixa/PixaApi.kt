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
        @Query("key") key : String = "33196303-6b7a37675179ae2b0300e41cd",
        @Query("per_page") per_page : Int = 3,
        @Query("page") page : Int = 1
    ):Call<PixaModel>

}