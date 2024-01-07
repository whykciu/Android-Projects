package com.example.project8android

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
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

    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val signInGoogleBtn = findViewById<Button>(R.id.btnGoogle)
        val registerBtn = findViewById<Button>(R.id.btnRegister)

        loginBtn.setOnClickListener{
            val loginData = Login(etLogin.text.toString(), etPassword.text.toString())
            login(loginData)
        }

        signInGoogleBtn.setOnClickListener{
            signInWithGoogle()
        }

        registerBtn.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also{
                startActivity(it)
            }
        }

        oneTapClient = Identity.getSignInClient(this)
        signUpRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId("TODO") //TODO: Add OAuth2.0 Client Identificator
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            .setAutoSelectEnabled(false)
            .build()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    val email = credential.id
                    val password = credential.password
                    when {
                        idToken != null || password != null -> {
                            Toast.makeText(applicationContext, "Successfully signed in by Google. Welcome, " + email, Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Log.d("GOOGLE_ERROR", "No ID token or password!")
                        }
                    }
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            Log.d("GOOGLE_ERROR", "One-tap dialog was closed.")
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            Log.d("GOOGLE_ERROR", "One-tap encountered a network error.")
                        }
                        else -> {
                            Log.d("GOOGLE_ERROR", "Couldn't get credential from result." +
                                    " (${e.localizedMessage})")
                        }
                    }
                }
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

    private fun signInWithGoogle(){
        oneTapClient.beginSignIn(signUpRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, REQ_ONE_TAP,
                        null, 0, 0, 0)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("GOOGLE_ERROR", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                Log.d("GOOGLE_ERROR", e.localizedMessage)
            }
    }

}