package com.example.fakeshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fakeshop.productlist.presentation.view.productslist.ProductsListFragment
import com.example.fakeshop.registration.presentation.view.RegistrationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main, ProductsListFragment()).commit()
    }
}