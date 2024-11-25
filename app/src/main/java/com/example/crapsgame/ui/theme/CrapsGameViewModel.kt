package com.example.crapsgame.ui.theme

import com.example.crapsgame.model.CrapsUiState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CrapsGameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CrapsUiState())
    val uiState: StateFlow<CrapsUiState> = _uiState.asStateFlow()
}