package com.example.productdetails.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.productdetails.domain.ProductImage

class DiffUtil(
    private val oldList: List<ProductImage>,
    private val newList: List<ProductImage>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].image == newList[newItemPosition].image
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].image == newList[newItemPosition].image -> false
            else -> true
        }
    }
}