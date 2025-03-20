package com.example.surflibrary.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BooksDetailScreen (
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    booksDetailViewModel: BooksDetailViewModel = hiltViewModel()
) {
}