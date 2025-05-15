package com.example.surflibrary.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surflibrary.data.book.utils.OperationResult
import com.example.surflibrary.domain.usecase.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    fun getAllBooks(query: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (val result = getAllBooksUseCase(query)) {
                is OperationResult.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            books = result.data,
                            errorMessage = null
                        )
                    }
                }
                is OperationResult.Error -> {
                    onError("Failed to load books")
                }
            }
        }
    }


    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                books = emptyList(),
                errorMessage = message
            )
        }
    }
}