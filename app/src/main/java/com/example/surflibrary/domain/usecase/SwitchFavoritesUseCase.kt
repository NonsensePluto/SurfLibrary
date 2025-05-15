package com.example.surflibrary.domain.usecase

import com.example.surflibrary.domain.models.BookModel
import com.example.surflibrary.domain.repository.BookRepository
import javax.inject.Inject

class SwitchFavoritesUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(book: BookModel) = bookRepository.switchFavorites(book)
}