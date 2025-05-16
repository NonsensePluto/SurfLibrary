package com.example.surflibrary.presentation.screens.main


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.Scaffold
import com.example.surflibrary.presentation.screens.books.BookGrid
import com.example.surflibrary.presentation.screens.navigation_menu.BottomNavigationBar
import com.example.surflibrary.presentation.screens.utils.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    onBookClick: (String) -> Unit,
    onNavigateToFavorites: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val state by mainViewModel.uiState.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    var active by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {//Установка поля поиска
            SearchBar(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                query = searchQuery,
                onSearch = {
                    mainViewModel.getAllBooks(searchQuery)
                },
                onQueryChange = { query ->
                    searchQuery = query
                    mainViewModel.getAllBooks(query)
                },
                active = active,
                onActiveChange = { active = false },
                placeholder = { Text("Поиск книг") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
            )
            {}
        },

        bottomBar = {//Установка нижнего меню

            BottomNavigationBar(
                Screens.SEARCH,
                onNavigateToSearch = {},
                onNavigateToFavorites = { onNavigateToFavorites() }
            )
        }
    ) { paddingValues ->
        Box(//Установка экрана в зависимости от состояния
            Modifier.fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                        CircularProgressIndicator()
                }

                state.errorMessage != null -> {
                    Text(
                        text = state.errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                    )
                }

                state.books.isEmpty() -> {
                    Text("Введите книгу для поиска")
                }

                else -> {
                    BookGrid(modifier = Modifier, state.books, onBookClick, 2)
                }
            }
        }
    }
}
