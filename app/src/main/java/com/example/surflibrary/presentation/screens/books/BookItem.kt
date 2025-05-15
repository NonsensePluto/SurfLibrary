package com.example.surflibrary.presentation.screens.books

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.surflibrary.domain.models.BookModel


@Composable
fun BookItem (
    modifier: Modifier = Modifier,
    book: BookModel,
    onBookClick: (bookId: String) -> Unit,
    viewModel: BookViewModel = hiltViewModel()
) {

    val isFavorite by viewModel.loadFavoriteStatus(book.id).collectAsStateWithLifecycle(false)

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onBookClick(book.id) },
        colors = CardColors(Color.White, Color.Black, Color.Gray, Color.Gray)
    ) {

        Column {
            Box {
                AsyncImage(
                    model = book.imageLink,
                    contentDescription = "Book image",
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    clipToBounds = true
                )
                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = { viewModel.switchFavorites(book) },
                    colors = IconButtonColors(Color.White, Color.White, Color.Gray, Color.Gray)
                ) {
                    Icon(
                        imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorites Button",
                        tint = if(isFavorite) Color.Red else Color.Black,
                    )
                }
            }
            Column {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
            }
        }
    }
}
