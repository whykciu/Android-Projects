package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.project5.fragments.BagFragment
import com.example.project5.fragments.PaymentsFragment
import com.example.project5.fragments.ShopFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var shopFragment: ShopFragment
    private lateinit var bagFragment: BagFragment
    private lateinit var paymentFragment: PaymentsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.navigationBar)
        shopFragment = ShopFragment()
        bagFragment = BagFragment()
        paymentFragment = PaymentsFragment()

        setCurrentFragment(shopFragment)

        bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.shop -> setCurrentFragment(shopFragment)
                R.id.bag -> setCurrentFragment(bagFragment)
                R.id.payments -> setCurrentFragment(paymentFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}