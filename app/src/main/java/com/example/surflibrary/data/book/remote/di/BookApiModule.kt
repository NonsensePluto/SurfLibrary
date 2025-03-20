package com.example.surflibrary.data.book.remote.di

import com.example.surflibrary.data.book.remote.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object BookApiModule {

    @Singleton
    @Provides
    fun providesBookApi(retrofit: Retrofit) = retrofit.create(BookApi::class.java)
}