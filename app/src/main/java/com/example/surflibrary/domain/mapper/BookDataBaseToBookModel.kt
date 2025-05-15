package com.example.surflibrary.domain.mapper

import com.example.surflibrary.data.book.database.entities.FavoriteBook
import com.example.surflibrary.domain.models.BookModel
import javax.inject.Inject

class BookDataBaseToBookModel @Inject constructor() : (FavoriteBook) -> BookModel {
    override operator fun invoke(favoriteBook: FavoriteBook): BookModel {

        return BookModel(
            id = favoriteBook.id,
            name = favoriteBook.title,
            author = favoriteBook.author,
            description = favoriteBook.description,
            imageLink = favoriteBook.imageLink
        )
    }
}