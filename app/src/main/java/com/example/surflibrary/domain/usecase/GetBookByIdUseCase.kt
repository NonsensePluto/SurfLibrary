package com.example.surflibrary.domain.usecase

import com.example.surflibrary.data.book.utils.OperationResult
import com.example.surflibrary.domain.models.BookModel
import com.example.surflibrary.domain.repository.BookRepository
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(id: Int): OperationResult<BookModel> =
        bookRepository.getBookById(id)
}