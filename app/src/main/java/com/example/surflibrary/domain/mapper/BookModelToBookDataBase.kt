package com.example.surflibrary.domain.mapper

import com.example.surflibrary.data.book.database.entities.FavoriteBook
import com.example.surflibrary.domain.models.BookModel
import javax.inject.Inject

class BookModelToBookDataBase @Inject constructor() : (BookModel) -> FavoriteBook {
    override operator fun invoke(bookModel: BookModel): FavoriteBook {

        return FavoriteBook(
            id = bookModel.id,
            title = bookModel.name,
            author = bookModel.author,
            description = bookModel.description,
            imageLink = bookModel.imageLink
        )
    }
}