//package com.example.fakeshop.productDetails
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.example.fakeshop.R
//import com.example.fakeshop.productlist.domain.list.Images
//
//
//class ProductDetailsAdapter : RecyclerView.Adapter<ImageHolder>() {
//    private var imagesList = mutableListOf<Images>()
//    private fun getItem(position: Int): Images = imagesList[position]
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
//        return ImageHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_image, parent, false)
//        )
//    }
//
//    override fun getItemCount() = imagesList.size
//
//    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
//        holder.bindPersonData(getItem(position))
//    }
//
//    fun setImages(newPerson: List<Images>) {
//        imagesList.clear()
//        imagesList.addAll(newPerson)
//        notifyDataSetChanged()
//
//    }
//}
//
//class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
//    private val actorImage = view.findViewById<ImageView>(R.id.item_image)
//
//    fun bindPersonData(image: Images) {
//        actorImage?.load(image.images)
//    }
//}