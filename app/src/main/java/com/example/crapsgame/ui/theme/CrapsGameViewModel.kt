package com.example.crapsgame.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.crapsgame.model.CrapsUiState
import androidx.lifecycle.ViewModel
import com.example.crapsgame.model.DiceItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CrapsGameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CrapsUiState())
    val uiState: StateFlow<CrapsUiState> = _uiState.asStateFlow()

    //get UI to hold the bet state
    private val _currentBet = MutableStateFlow(0.00)
    val currentBet: StateFlow<Double> = _currentBet

    //update the actual bet
    fun updateBet(newBet : Double) {
        _currentBet.value = newBet
    }

    //hold isPointSet
    private val _isPointSet = MutableStateFlow(false)
    val isPointSet : StateFlow<Boolean> = _isPointSet

    //update isPointSet
    fun updateIsPointSet(isPointSet: Boolean){
        _isPointSet.value = isPointSet
    }

    //get UI to hold the Point state, which can be null
    private val _currentPoint = MutableStateFlow(0)
    val currentPoint: StateFlow<Int> = _currentPoint

    //update the Point
    fun updatePoint(newPoint: Int) {
        _currentPoint.value = newPoint
    }

    // Ui state for isFirstRoll Bool
    private val _isFirstRoll = MutableStateFlow(true)
    val isFirstRoll: StateFlow<Boolean> = _isFirstRoll

    //function to update isFirstRoll
    fun updateFirstRoll(isFirstRollUpdate : Boolean){
        _isFirstRoll.value = isFirstRollUpdate
    }

    //Ui state for amountWon
    private val _amountWon = MutableStateFlow(0.00)
    val amountWon: StateFlow<Double> = _amountWon

    //function to update amountWon
    fun updateAmountWon(payout : Double) {
        _amountWon.value = payout
    }
    // Ui state for Bankroll
    private val _bankroll = MutableStateFlow(100.00)
    val bankroll: StateFlow<Double> = _bankroll

    //function to update isFirstRoll
    fun updateBankRoll(newBalance : Double){
        _bankroll.value = newBalance
    }

    // Ui state for dice images
    private val _isBlack = MutableStateFlow(true)
    val isBlack: StateFlow<Boolean> = _isBlack

    //function to update isFirstRoll
    fun updateIsBlack(isBlackUpdate : Boolean){
        _isBlack.value = isBlackUpdate
    }

    fun getDiceImages(isBlack:Boolean): Pair<Int, Int> {
        val diceItem = DiceItem(resultDie1 = 6, resultDie2 = 1, isBlack)
        return diceItem.getImageResources()
    }

}