package com.example.project8android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val baseUrl = "http://192.168.1.13:8080/"
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(baseUrl)
        .build()
        .create(RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val registerBtn = findViewById<Button>(R.id.btnRegister)

        loginBtn.setOnClickListener{
            val loginData = Login(etLogin.text.toString(), etPassword.text.toString())
            login(loginData)
        }

        registerBtn.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also{
                startActivity(it)
            }
        }

    }

    private fun login(loginData: Login){

        val retrofitData = retrofitBuilder.sendLoginData(loginData)

        retrofitData.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful && response.body()!!.matches("Success".toRegex())){
                    Toast.makeText(applicationContext, "Successfully logged in.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Invalid credentials, try again.", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("ERROR","Login failure: " + t.message)
            }
        })


    }

}