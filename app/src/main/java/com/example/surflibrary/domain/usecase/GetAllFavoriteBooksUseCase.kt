package com.example.surflibrary.domain.usecase

import com.example.surflibrary.domain.models.BookModel
import com.example.surflibrary.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(): Flow<List<BookModel>> = bookRepository.getFavoritesBooks()
}