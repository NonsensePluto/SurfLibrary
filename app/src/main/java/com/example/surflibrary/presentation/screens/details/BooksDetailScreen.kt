package com.example.surflibrary.presentation.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.surflibrary.presentation.screens.books.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksDetailScreen (
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    booksDetailViewModel: BooksDetailViewModel = hiltViewModel(),
    bookViewModel: BookViewModel = hiltViewModel(),
) {
    val state by booksDetailViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val isFavorite by state.bookModel?.let { book ->
        bookViewModel.loadFavoriteStatus(book.id)
            .collectAsState(initial = false)
    } ?: remember { mutableStateOf(false) }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали книги") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "GoBack")
                    }
                },
                actions = {
                    state.bookModel?.let { book ->
                        IconButton(
                            onClick = { bookViewModel.switchFavorites(book) }
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                tint = if (isFavorite) Color.Red else Color.Black,
                                contentDescription = "Favs button"

                            )
                        }

                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator()
                }

                state.errorMessage != null -> {
                    if (isFavorite) {
                        ShowBookDetails(
                            modifier,
                            state.bookModel,
                            scrollState,
                            paddingValues
                        )
                    } else {
                        Text(
                            text = state.errorMessage!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                else -> {
                    ShowBookDetails(
                        modifier,
                        state.bookModel,
                        scrollState,
                        paddingValues
                    )
                }
            }
        }
    }
}

