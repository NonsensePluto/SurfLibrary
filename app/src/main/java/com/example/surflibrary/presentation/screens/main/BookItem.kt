package com.example.surflibrary.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.surflibrary.domain.models.BookModel
import com.example.surflibrary.presentation.ui.theme.SurfLibraryTheme


@Composable
fun BookItem (
    modifier: Modifier = Modifier,
    book: BookModel,
    onBookClick: (bookId: String) -> Unit
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onBookClick(book.id) }
    ) {
        Column( modifier = Modifier.fillMaxWidth().padding(16.dp)){
            AsyncImage(
                model = book.imageLink,
                contentDescription = "Book image",
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = book.author ?: "Автор неизвестен",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookItem() {
    SurfLibraryTheme {
        BookItem(

            book = BookModel(
                id = "afasfafas",
                name = "Harry Potter",
                author = "Rose",
                description = null,
                imageLink = null
            ),
            onBookClick = {},
        )
    }
}
