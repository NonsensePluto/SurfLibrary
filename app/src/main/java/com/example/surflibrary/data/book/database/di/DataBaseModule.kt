package com.example.surflibrary.data.book.database.di

import android.content.Context
import androidx.room.Room
import com.example.surflibrary.data.book.database.appdatabase.AppDatabase
import com.example.surflibrary.data.book.database.dao.FavoritesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Favorites_books"
        ).build()
    }

    @Provides
    fun provideFavoritesDao(database: AppDatabase): FavoritesDAO {
        return database.favoritesDao()
    }
}