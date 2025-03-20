package com.example.surflibrary.presentation.screens.details

import com.example.surflibrary.domain.models.BookModel

data class BooksDetailScreenState(
    val id: Int = -1,
    val bookModel: BookModel? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
