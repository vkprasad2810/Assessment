package com.example.assessment.retrofit

import com.example.assessment.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("posts")
    suspend fun postList(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<PostResponse>


}
