package com.example.crapsgame.ui.theme

import com.example.crapsgame.model.CrapsUiState
import androidx.lifecycle.ViewModel
import com.example.crapsgame.model.DiceItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CrapsGameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CrapsUiState())
    val uiState: StateFlow<CrapsUiState> = _uiState.asStateFlow()

    fun getDiceImages(isBlack:Boolean): Pair<Int, Int> {
        val diceItem = DiceItem(resultDie1 = 6, resultDie2 = 1, isBlack)
        return diceItem.getImageResources()
    }

}