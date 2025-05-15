package com.example.surflibrary.presentation.screens.main

import com.example.surflibrary.domain.models.BookModel

data class MainScreenState(
    val books: List<BookModel> = emptyList(),
    val isLoading: Boolean = false,
    val toFavorites: Boolean = false,
    val errorMessage: String? = null
)
