package com.example.surflibrary.data.book.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_book")
data class FavoriteBook(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val description: String,
    val imageLink: String?,
)