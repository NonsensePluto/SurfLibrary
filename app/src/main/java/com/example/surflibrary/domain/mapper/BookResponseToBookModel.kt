package com.example.surflibrary.domain.mapper

import com.example.surflibrary.data.book.remote.models.BookItem
import com.example.surflibrary.domain.models.BookModel
import javax.inject.Inject

class BookResponseToBookModel @Inject constructor() : (BookItem) -> BookModel {
    override operator fun invoke(bookItem: BookItem): BookModel =
        BookModel(
            id = bookItem.id,
            name = bookItem.volumeInfo.title ?: "",
            author = bookItem.volumeInfo.authors.toString(),
            description = bookItem.volumeInfo.description ?: "",
            imageLink = bookItem.volumeInfo.imageLinks?.thumbnail
        )
}