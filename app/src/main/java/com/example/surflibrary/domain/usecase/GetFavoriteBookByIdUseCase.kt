package com.example.surflibrary.domain.usecase

import com.example.surflibrary.domain.models.BookModel
import com.example.surflibrary.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBookByIdUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(id: String): Flow<BookModel?> = bookRepository.findFavoriteBookById(id)
}