package com.example.surflibrary.domain.models

data class BookModel(
    val id: String,
    val name: String,
    val author: String?,
    val description: String?,
    val imageLink: String?
)
