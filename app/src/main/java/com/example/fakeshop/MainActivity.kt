package com.example.fakeshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fakeshop.presentation.view.productslist.ProductsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main, ProductsListFragment()).commit()
    }
}