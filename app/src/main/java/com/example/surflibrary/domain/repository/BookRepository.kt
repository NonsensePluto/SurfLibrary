package com.example.surflibrary.domain.repository

import com.example.surflibrary.data.book.remote.BookRemoteDataSource
import com.example.surflibrary.data.book.utils.OperationResult
import com.example.surflibrary.domain.mapper.BookResponseToBookModel
import com.example.surflibrary.domain.models.BookModel
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val mapper: BookResponseToBookModel
) {

//    suspend fun getAllPosts(q: String): OperationResult<List<BookModel>> {
//        val books = remoteDataSource.getAllBooks(query = q)
//        return books
//    }

    suspend fun getAllBooks(query: String): OperationResult<List<BookModel>> {
        return when (val result = remoteDataSource.getAllBooks(query)) {
            is OperationResult.Success -> {
                val books = result.data.items?.map { mapper(it) } ?: emptyList()
                OperationResult.Success(books)
            }
            is OperationResult.Error -> result
        }
    }

    //    suspend fun getBookById(id: Int): OperationResult<BookModel> {
//        val result = remoteDataSource.getBookById(id)
//        when(result) {
//            is OperationResult.Success -> OperationResult.Success(mapper(result.data))
//        }
//    }
    suspend fun getBookById(id: String): OperationResult<BookModel> {
        return when (val result = remoteDataSource.getBookById(id)) {
            is OperationResult.Success -> OperationResult.Success(mapper(result.data))
            is OperationResult.Error -> result
        }
    }
}