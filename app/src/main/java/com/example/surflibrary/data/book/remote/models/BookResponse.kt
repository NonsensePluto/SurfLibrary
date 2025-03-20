package com.example.surflibrary.data.book.remote.models

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("items")
    val items: List<BookItem>? = emptyList()
)