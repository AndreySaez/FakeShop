package com.example.fakeshop.productlist.presentation.view.productslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fakeshop.R
import com.example.coremodule.productlist.Product

class ProductsListAdapter(
    private val clickListener: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var productsList = listOf<Product>()
    private var showLoading: Boolean = false
    override fun getItemViewType(position: Int): Int {
        return if (position == productsList.size) {
            LoadingViewHolder.VIEW_TYPE
        } else {
            ProductsListViewHolder.VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ProductsListViewHolder.VIEW_TYPE -> ProductsListViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product_list, parent, false)
            )

            LoadingViewHolder.VIEW_TYPE -> LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_loading,
                    parent, false
                )
            )

            else -> throw IllegalStateException("Not found view type")
        }
    }

    override fun getItemCount(): Int = productsList.size + if (showLoading) 1 else 0


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == productsList.size || holder !is ProductsListViewHolder) return
        holder.bindData(productsList[position])
        holder.itemView.setOnClickListener {
            clickListener(productsList[position])
        }
    }

    fun bindProductList(newList: List<Product>, showLoading: Boolean = false) {
        val previousLoading = this.showLoading
        this.showLoading = showLoading
        if (!showLoading && previousLoading) {
            notifyItemRemoved(productsList.size)
        }
        val diffUtil = DiffUtilCallback(productsList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        productsList = newList
        diffResults.dispatchUpdatesTo(this)
    }
}

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val VIEW_TYPE = 2
    }
}

class ProductsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val name = itemView.findViewById<TextView>(R.id.name)
    private val oldPrice = itemView.findViewById<TextView>(R.id.price)
    fun bindData(product: Product) {
        val imageData = if (product.images.isNotEmpty()) {
            product.images[0]
        } else {
            R.drawable.error_image_placeholder
        }
        image.load(imageData) {
            placeholder(R.drawable.error_image_placeholder)
            fallback(R.drawable.error_image_placeholder)
            error(R.drawable.error_image_placeholder)
        }
        name.text = product.name.ifEmpty {
            itemView.context.getString(R.string.information_is_issing)
        }
        oldPrice.text = itemView.context.getString(R.string.price_template, product.price)

    }

    companion object {
        const val VIEW_TYPE = 1
    }
}
