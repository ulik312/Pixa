package com.example.pixa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixa.databinding.ItemImageBinding
//import com.example.pixal.databinding.ItemImageBinding

class ImageAdapter(private val list: List<ImageModel>) : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ImageHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(imageModel: ImageModel) {
            binding.imageView.load(imageModel.largeImageURL)

        }

    }
}