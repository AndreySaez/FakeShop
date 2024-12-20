package com.example.productdetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coremodule.Navigator
import com.example.coremodule.productlist.Product
import com.example.productdetails.R
import com.example.productdetails.domain.ProductImage

internal class ProductDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.details_recycler_view)
        val adapter = ProductDetailsAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isEnabled = false
            Navigator.closeDetails()
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        val productData = arguments?.getParcelable(PRODUCT_KEY) as? Product
            ?: throw IllegalStateException("No data")
        bindData(productData, view)
        val imagesList = productData.images.map { ProductImage(it) }
        adapter.setImages(imagesList)

    }

    private fun bindData(product: Product, view: View) {
        view.findViewById<TextView>(R.id.price).apply {
            text = view.context.getString(R.string.price_template, product.price)
        }
        view.findViewById<TextView>(R.id.title_details).apply {
            text = product.name
        }
        view.findViewById<TextView>(R.id.description).apply {
            text = product.description
        }
    }

    private val PRODUCT_KEY = "Product"
}