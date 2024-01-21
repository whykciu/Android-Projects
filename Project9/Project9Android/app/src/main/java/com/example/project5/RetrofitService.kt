package com.example.project5

import com.example.project5.models.PaymentData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {
    @POST("pay")
    fun pay(@Body paymentData: PaymentData): Call<String>
}