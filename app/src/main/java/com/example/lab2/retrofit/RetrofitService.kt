package com.example.lab2.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitService {
    @GET
    suspend fun getData(@Url url: String) : Response<RssFeed>
}