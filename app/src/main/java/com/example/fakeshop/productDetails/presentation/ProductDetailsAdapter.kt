package com.example.fakeshop.productDetails.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.fakeshop.R
import com.example.fakeshop.productDetails.domain.ProductImage


class ProductDetailsAdapter : RecyclerView.Adapter<ImageHolder>() {
    private var imagesList: List<ProductImage> = emptyList()
    private fun getItem(position: Int): ProductImage = imagesList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        )
    }

    override fun getItemCount() = imagesList.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bindPersonData(getItem(position))
    }

    fun setImages(newImages: List<ProductImage>) {
        val diffUtil = DiffUtil(imagesList, newImages)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        imagesList = newImages
        diffResults.dispatchUpdatesTo(this)
    }
}

class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val actorImage = view.findViewById<ImageView>(R.id.item_image)

    fun bindPersonData(image: ProductImage) {
        actorImage?.apply {
            load(image.image)
            RoundedCornersTransformation(25f)
        }
    }
}