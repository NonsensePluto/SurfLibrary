package com.example.surflibrary.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.surflibrary.domain.usecase.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BooksDetailViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow(BooksDetailScreenState())
    val uiState: StateFlow<BooksDetailScreenState> = _uiState
}