package com.example.pixa

data class PixaModel(
    val hits: ArrayList<ImageModel>
)

data class ImageModel(
    val largeImageURL: String
)