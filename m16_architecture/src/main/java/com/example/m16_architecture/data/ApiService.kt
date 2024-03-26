package com.example.m16_architecture.data


import com.example.m16_architecture.data.pojo.ActivityFromBoredApi
import retrofit2.http.GET

interface ApiService {
    @GET("activity")
    suspend fun loadActivity(): ActivityFromBoredApi
}