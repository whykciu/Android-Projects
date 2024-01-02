package com.example.project8android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    @POST("login")
    fun sendLoginData(@Body loginData: Login): Call<String>

    @POST("register")
    fun sendRegisterData(@Body registerData: User): Call<String>

}