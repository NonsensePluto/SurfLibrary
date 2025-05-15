package com.example.surflibrary.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import com.example.surflibrary.domain.usecase.GetAllFavoriteBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewModelScope

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoriteBooksUseCase: GetAllFavoriteBooksUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllFavorites()
    }

    fun getAllFavorites() {
        viewModelScope.launch {
            getAllFavoriteBooksUseCase()
                .collect { books ->
                    _uiState.update { state ->
                        state.copy(
                            books = books
                        )
                    }
                }
        }
    }
}