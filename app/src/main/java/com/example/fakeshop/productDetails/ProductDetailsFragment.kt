package com.example.fakeshop.productDetails

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fakeshop.R
import com.example.fakeshop.productlist.domain.list.Product

class ProductDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigator.closeDetails()
        }
        val productData = arguments?.getParcelable(PRODUCT_KEY) as? Product
            ?: throw IllegalStateException("No data")
        bindData(productData, view)

    }

    private fun bindData(product: Product, view: View) {
        view.findViewById<TextView>(R.id.price).apply {
            text = view.context.getString(R.string.price_template, product.price)
            paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        view.findViewById<TextView>(R.id.discounted_price).apply {
            text = view.context.getString(R.string.price_template, product.discountPrice)
        }
        view.findViewById<TextView>(R.id.title_details).apply {
            text = product.name
        }
        view.findViewById<TextView>(R.id.description).apply {
            text = product.description
        }
    }


    companion object {

        private const val PRODUCT_KEY = "Product"
        fun newInstance(product: Product): ProductDetailsFragment {
            return ProductDetailsFragment().apply {
                arguments = bundleOf(PRODUCT_KEY to product)
            }
        }
    }
}