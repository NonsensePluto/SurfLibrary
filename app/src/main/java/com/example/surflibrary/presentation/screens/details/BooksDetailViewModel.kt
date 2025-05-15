package com.example.surflibrary.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.surflibrary.data.book.utils.OperationResult
import com.example.surflibrary.domain.usecase.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksDetailViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _uiState = MutableStateFlow(BooksDetailScreenState())
    val uiState: StateFlow<BooksDetailScreenState> = _uiState

    init {
        // Получаем ID книги из аргументов навигации
        val bookId = savedStateHandle.get<String>("bookId")
        if (bookId != null) {
            loadBookDetails(bookId)
        } else {
            _uiState.update { it.copy(errorMessage = "Book ID not found") }
        }
    }

    private fun loadBookDetails(bookId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getBookByIdUseCase(bookId)) {
                is OperationResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            bookModel = result.data,
                            errorMessage = null
                        )
                    }
                }

                is OperationResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Failed to load book details"
                        )
                    }
                }
            }
        }
    }
}