package com.example.crapsgame.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class CrapsUiState(
    val bankRoll: Double = 100.00
)