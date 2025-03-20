package com.example.surflibrary.domain.mapper

import com.example.surflibrary.data.book.remote.models.BookItem
import com.example.surflibrary.domain.models.BookModel
import javax.inject.Inject

class BookResponseToBookModel @Inject constructor() : (BookItem) -> BookModel {
    override operator fun invoke(bookItem: BookItem): BookModel {

        val authors = bookItem.volumeInfo.authors
        val authorText = if (authors.isNullOrEmpty()) {
            "Автор не найден"
        } else {
            authors.joinToString(", ").removeSurrounding("[", "]")
        }

        return BookModel(
            id = bookItem.id,
            name = bookItem.volumeInfo.title ?: "Название не указано",
            author = authorText,
            description = bookItem.volumeInfo.description ?: "Описание отсутствует",
            imageLink = bookItem.volumeInfo.imageLinks?.thumbnail
        )
    }
}