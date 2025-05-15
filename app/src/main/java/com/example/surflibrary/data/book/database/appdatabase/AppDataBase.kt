package com.example.surflibrary.data.book.database.appdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.surflibrary.data.book.database.dao.FavoritesDAO
import com.example.surflibrary.data.book.database.entities.FavoriteBook

@Database(
    entities = [FavoriteBook::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDAO
}