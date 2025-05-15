package com.example.surflibrary.domain.repository

import com.example.surflibrary.data.book.database.reposiotory.FavoritesBookRepository
import com.example.surflibrary.data.book.remote.BookRemoteDataSource
import com.example.surflibrary.data.book.utils.OperationResult
import com.example.surflibrary.domain.mapper.BookDataBaseToBookModel
import com.example.surflibrary.domain.mapper.BookModelToBookDataBase
import com.example.surflibrary.domain.mapper.BookResponseToBookModel
import com.example.surflibrary.domain.models.BookModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val bookResponseMapper: BookResponseToBookModel,
    private val favoritesBookRepository: FavoritesBookRepository,
    private val bookModelToBookDataBase: BookModelToBookDataBase,
    private val bookDataBaseMapper: BookDataBaseToBookModel,
) {

    suspend fun getAllBooks(query: String): OperationResult<List<BookModel>> {
        return when (val result = remoteDataSource.getAllBooks(query)) {
            is OperationResult.Success -> {
                val books = result.data.items?.map { bookResponseMapper(it) } ?: emptyList()
                OperationResult.Success(books)
            }
            is OperationResult.Error -> result
        }
    }

    suspend fun getBookById(id: String): OperationResult<BookModel> {
        return when (val result = remoteDataSource.getBookById(id)) {
            is OperationResult.Success -> OperationResult.Success(bookResponseMapper(result.data))
            is OperationResult.Error -> result
        }
    }

    suspend fun switchFavorites(book: BookModel) {
        favoritesBookRepository.switchFavoriteStatus(bookModelToBookDataBase(book))
    }

    fun getFavoritesBooks(): Flow<List<BookModel>> {
        return favoritesBookRepository.getAllFavorites()
            .map { book ->
                book.map { favoriteBook ->
                    bookDataBaseMapper(favoriteBook)
                }
            }
    }

//    fun getFavoriteBookById(id: String): Flow<BookModel?> {
//        return favoritesBookRepository.findById(id)?.let { bookDataBaseMapper(it) }
//    }

    fun isFavorite(bookId: String): Flow<Boolean> = favoritesBookRepository.isFavorite(bookId)
}