package com.example.surflibrary.data.book.remote.models

import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("thumbnail")
    val thumbnail: String?
)