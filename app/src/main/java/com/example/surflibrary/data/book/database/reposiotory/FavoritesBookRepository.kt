package com.example.surflibrary.data.book.database.reposiotory

import com.example.surflibrary.data.book.database.dao.FavoritesDAO
import com.example.surflibrary.data.book.database.entities.FavoriteBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import android.util.Log


class FavoritesBookRepository @Inject constructor(
    private val dao: FavoritesDAO
) {

    suspend fun switchFavoriteStatus(book: FavoriteBook) {
        if(dao.isFavorite(book.id).first()) {
            dao.removeFromFavorites(book)
        } else {
            Log.d("DB_ADD", "Try to add book ${book.id}")
            dao.addToFavorites(book)
            Log.d("DB_ADD_SUCCESS", "book ${book.id} was added")

        }
    }

    fun getAllFavorites(): Flow<List<FavoriteBook>> = dao.getAllFavorites()

    fun isFavorite(bookId: String): Flow<Boolean> = dao.isFavorite(bookId)

    fun findById(bookId: String): Flow<FavoriteBook?> = dao.findById(bookId)
}