package com.picker.moth6_2_dz.selected

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picker.moth6_2_dz.utils.loadImage
import com.picker.moth6_2_dz.databinding.GalleryItemBinding
import com.picker.moth6_2_dz.models.ImageModel

class SelectedAdapter : RecyclerView.Adapter<SelectedAdapter.ImageViewHolder>() {

    private val listImage = arrayListOf<ImageModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    @SuppressLint("NotifyDataSetChanged")
    fun setImageList(img: List<ImageModel>) {
        listImage.clear()
        listImage.addAll(img)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(listImage[position])
    }

    override fun getItemCount() = listImage.size

    inner class ImageViewHolder(private val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel) {
            binding.imgGallery.loadImage(imageModel.image)
        }
    }
}
