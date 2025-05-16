package com.example.surflibrary.presentation.screens.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.surflibrary.domain.models.BookModel

@Composable
fun ShowBookDetails(modifier: Modifier, book: BookModel?, scrollState: ScrollState, paddingValues: PaddingValues) {
    if (book != null) {
        Column(
            modifier = modifier.fillMaxSize()
                .verticalScroll(scrollState)
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