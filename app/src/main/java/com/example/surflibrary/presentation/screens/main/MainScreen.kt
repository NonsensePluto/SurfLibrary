package com.example.surflibrary.presentation.screens.main

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier,
//    onBookClick: (bookId: Int) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val state by mainViewModel.uiState.collectAsStateWithLifecycle()

    val errorMessage = state.errorMessage

    val contentModifier = Modifier.fillMaxSize()

    var searchQuery by remember { mutableStateOf("") }

    var active by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        SearchBar(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            query = searchQuery,
            onSearch = { mainViewModel.getAllPosts(searchQuery) } ,
            onQueryChange = { query ->
                searchQuery = query
                mainViewModel.getAllPosts(query)
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Поиск книг") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }

        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.errorMessage != null -> {
                    Text(
                        text = state.errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {
                    GridItemSpan(
                        2
                    )
                }
            }


        }
    }
}