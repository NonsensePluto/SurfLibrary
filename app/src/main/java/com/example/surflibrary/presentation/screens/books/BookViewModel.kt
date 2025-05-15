package com.example.surflibrary.presentation.screens.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surflibrary.domain.models.BookModel
import com.example.surflibrary.domain.usecase.IsFavoriteUseCase
import com.example.surflibrary.domain.usecase.SwitchFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val switchFavoritesUseCase: SwitchFavoritesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {

    fun switchFavorites(book: BookModel) {
        viewModelScope.launch {
            switchFavoritesUseCase(book)
        }
    }

    fun loadFavoriteStatus(bookId: String): Flow<Boolean> {
        return isFavoriteUseCase(bookId)
    }
}