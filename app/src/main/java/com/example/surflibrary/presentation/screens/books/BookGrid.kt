package com.example.surflibrary.presentation.screens.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.surflibrary.domain.models.BookModel

@Composable
fun BookGrid(modifier: Modifier, books: List<BookModel>, onBookClick: (String) -> Unit, countOfColumns: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(countOfColumns),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(books) { book ->
            BookItem(
                book = book,
                onBookClick = { onBookClick(book.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBookClick(book.id) }
                    .padding(8.dp),
            )
        }
    }
}