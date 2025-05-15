package com.example.surflibrary.data.book.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.surflibrary.data.book.database.entities.FavoriteBook
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(book: FavoriteBook)

    @Delete
    suspend fun removeFromFavorites(book: FavoriteBook)

    @Query("SELECT * FROM favorite_book")
    fun getAllFavorites(): Flow<List<FavoriteBook>>

    @Query("SELECT EXISTS (SELECT * FROM favorite_book WHERE id = :bookId)")
    fun isFavorite(bookId: String): Flow<Boolean>

    @Query("SELECT * FROM favorite_book WHERE id = :bookId")
    fun findById(bookId: String): Flow<FavoriteBook?>
}