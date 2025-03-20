package com.example.surflibrary.data.book.remote.models

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("title")
    val title: String?,

    @SerializedName("authors")
    val authors: List<String>?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("imageLinks")
    val imageLinks: ImageLinks?
)