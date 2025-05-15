package com.example.surflibrary.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.surflibrary.domain.models.BookModel

@Composable
fun BookDetailContent(
    book: BookModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        AsyncImage(
            model = book.imageLink,
            contentDescription = null,
            modifier = Modifier
                .height(350.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )


        Text(
            text = book.name,
            style = MaterialTheme.typography.headlineMedium
        )


        Text(
            text = "Автор: ${book.author}",
            style = MaterialTheme.typography.bodyLarge
        )


        book.description?.let {

                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )

        }
    }
}