package com.example.swiggyinterview.data.remote.api

import retrofit2.http.GET

interface ApiService {
    @GET("/user")
    suspend fun getUserList(): List<String>
}