package com.example.surflibrary.presentation.screens.favorites

import com.example.surflibrary.domain.models.BookModel

data class FavoritesScreenState (
    val books: List<BookModel> = emptyList(),
)
