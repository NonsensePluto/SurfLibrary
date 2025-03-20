package com.example.surflibrary.data.book.remote

import com.example.surflibrary.data.book.remote.models.BookItem
import com.example.surflibrary.data.book.remote.models.BookResponse
import com.example.surflibrary.data.book.utils.BaseRemoteDataSource
import com.example.surflibrary.data.book.utils.OperationResult
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val bookApi: BookApi
): BaseRemoteDataSource() {

    suspend fun getAllBooks(query: String): OperationResult<BookResponse> =
        safeApiCall { bookApi.getAllBooks(query) }

    suspend fun getBookById(id: String): OperationResult<BookItem> =
        safeApiCall { bookApi.getBookById(id) }

}