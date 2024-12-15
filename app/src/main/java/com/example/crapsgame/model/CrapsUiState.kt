package com.example.crapsgame.model

data class CrapsUiState(
    val bankRoll: Double = 100.00,
    val betAmount: Double = 0.00,
    val isDiceBlack: Boolean = true,
    val currentPoint: Int = 0
)
