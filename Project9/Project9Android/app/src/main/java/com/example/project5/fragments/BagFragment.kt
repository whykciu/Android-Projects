package com.example.project5.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project5.adapters.BagListAdapter
import com.example.project5.models.PaymentData
import com.example.project5.R
import com.example.project5.RetrofitService
import com.example.project5.models.SharedViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.RoundingMode

class BagFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: BagListAdapter
    private lateinit var sharedViewModel: SharedViewModel
    private val baseUrl = "http://192.168.1.27:8080/"
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTotal = view.findViewById<TextView>(R.id.tvTotal)
        val btnCheckout = view.findViewById<Button>(R.id.btnCheckout)
        val etCardNumber = view.findViewById<EditText>(R.id.etCardNumber)
        val etExp1 = view.findViewById<EditText>(R.id.etExp1)
        val etExp2 = view.findViewById<EditText>(R.id.etExp2)
        val etCvv = view.findViewById<EditText>(R.id.etCVV)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        var paymentData: PaymentData
        val total = sharedViewModel.getTotal().value!!.setScale(2, RoundingMode.CEILING)

        btnCheckout.setOnClickListener {
            paymentData = PaymentData(etCardNumber.text.toString(), etExp1.text.toString() + "/" + etExp2.text.toString(), etCvv.text.toString(), total)
            pay(paymentData)
        }

        recyclerView = view.findViewById(R.id.rvBagList)

        recyclerViewAdapter = BagListAdapter(sharedViewModel, sharedViewModel.getBagProducts().value!!)
        sharedViewModel.getBagProducts().observe(viewLifecycleOwner) { bagProducts ->
            recyclerViewAdapter.updateData(bagProducts)
        }
        sharedViewModel.getTotal().observe(viewLifecycleOwner) { total ->
            val totalSum = total.setScale(2, RoundingMode.CEILING)
            tvTotal.text = "Total: $totalSum zł"
        }

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = layoutManager

    }

    private fun pay(paymentData: PaymentData){

        val retrofitData = retrofitBuilder.pay(paymentData)

        retrofitData.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful && response.body()!!.matches("Success".toRegex())){
                    Toast.makeText(context, "Payment successful.", Toast.LENGTH_LONG).show()
                    sharedViewModel.buyItems(paymentData)
                } else {
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("ERROR","Login failure: " + t.message)
            }
        })

    }


}