package com.example.pixa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.pixa.databinding.ItemImageBinding
//import com.example.pixal.databinding.ItemImageBinding

class ImageAdapter() :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var list: ArrayList<ImageModel> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }
    fun setImages(list: ArrayList<ImageModel>) {
        this.list.addAll(list)
        notifyItemChanged(list.size - 1)

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onbind(list[position])
    }
    //fun setChanged{ notifyItemMoved(getAdapterPosition(),list.size -1)}

    override fun getItemCount() = list.size


    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onbind(image: ImageModel) {

            Glide.with(itemView.context).load(image.largeImageURL)
                .into(binding.image)
            //binding.image.load(image.largeImageUrl)

        }

    }
}