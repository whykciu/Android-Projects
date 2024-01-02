package com.example.project8android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {


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
        setContentView(R.layout.register_layout)

        val etName = findViewById<EditText>(R.id.etRegisterName)
        val etSurname = findViewById<EditText>(R.id.etRegisterSurname)
        val etEmail = findViewById<EditText>(R.id.etRegisterEmail)
        val etLogin = findViewById<EditText>(R.id.etRegisterLogin)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        val registerButton = findViewById<Button>(R.id.btnAddUser)
        val backButton = findViewById<Button>(R.id.btnBack)

        registerButton.setOnClickListener {
            register(User(0, etName.text.toString(), etSurname.text.toString(), etEmail.text.toString(), etLogin.text.toString(), etPassword.text.toString()))
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }

    }

    private fun register(userData: User){

        val retrofitData = retrofitBuilder.sendRegisterData(userData)

        retrofitData.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    if(response.body()!!.matches("Success".toRegex())){
                        Toast.makeText(applicationContext, "Successfully registered.", Toast.LENGTH_LONG).show()
                    } else if(response.body()!!.matches("Fail".toRegex())){
                        Toast.makeText(applicationContext, "Login name taken, try again with a new one.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Error while registering, try again.", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("ERROR","Login failure: " + t.message)
            }
        })

    }

}