package com.example.surflibrary.domain.usecase

import com.example.surflibrary.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(bookId: String): Flow<Boolean> = bookRepository.isFavorite(bookId)
}