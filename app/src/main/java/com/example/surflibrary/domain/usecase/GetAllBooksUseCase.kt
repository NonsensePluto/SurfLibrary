package com.example.surflibrary.domain.usecase

import com.example.surflibrary.data.book.utils.OperationResult
import com.example.surflibrary.domain.models.BookModel
import com.example.surflibrary.domain.repository.BookRepository
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(query: String): OperationResult<List<BookModel>> =
        bookRepository.getAllBooks(query)
}