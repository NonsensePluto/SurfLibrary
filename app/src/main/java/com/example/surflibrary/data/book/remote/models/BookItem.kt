package com.example.surflibrary.data.book.remote.models

import com.google.gson.annotations.SerializedName

data class BookItem(
    @SerializedName("id")
    val id: String ,

    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo
)