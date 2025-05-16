package com.example.surflibrary.presentation.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.surflibrary.presentation.screens.books.BookGrid
import com.example.surflibrary.presentation.screens.navigation_menu.BottomNavigationBar
import com.example.surflibrary.presentation.screens.utils.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier,
    onBookClick: (String) -> Unit,
    onNavigateToSearch: () -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Избранное") })
        },
        bottomBar = {
            BottomNavigationBar(
                Screens.FAVORITES,
                onNavigateToSearch = { onNavigateToSearch() },
                onNavigateToFavorites = {}
            )
        }
    ) { paddingValues ->
        Box (
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.books.isEmpty() -> {
                    Text(
                        text = "Вы еще не добавляли книги в избранное",
                    )
                }

                else -> {
                    BookGrid(
                        modifier = Modifier,
                        state.books,
                        onBookClick,
                        2
                    )
                }
            }

        }
    }
}
