package com.example.surflibrary.presentation.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.util.splitToIntList
import com.example.surflibrary.presentation.screens.books.BookViewModel
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksDetailScreen (
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    booksDetailViewModel: BooksDetailViewModel = hiltViewModel(),
    bookViewModel: BookViewModel = hiltViewModel(),
) {
    val state by booksDetailViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState() // Добавляем состояние прокрутки

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
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.errorMessage!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            else -> {
                val book = state.bookModel
                if (book != null) {
                    Column(
                        modifier = modifier.fillMaxSize()
                            .verticalScroll(scrollState)//Прокрутка информации о книге
                    ) {
                        BookDetailContent(
                            book = book,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                } else {
                    Text(
                        text = "Данные о книге не найдены",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}