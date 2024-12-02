package com.example.crapsgame.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.crapsgame.model.CrapsUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.crapsgame.CrapsGameApplication
import com.example.crapsgame.data.UserPreferencesRepository
import com.example.crapsgame.model.DiceItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class CrapsGameViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {

    private val _bankroll = MutableStateFlow(100.00)
    private val _currentBet = MutableStateFlow(0.00)


    //private val _uiState = MutableStateFlow(CrapsUiState(isDiceBlack = false))
    //val uiState: StateFlow<CrapsUiState> = _uiState.asStateFlow()
    val uiState: StateFlow<CrapsUiState> =
        userPreferencesRepository.isDiceBlack
            .combine(_bankroll) {isDiceBlack, bankroll ->
                isDiceBlack to bankroll
            }
            .combine(_currentBet) { (isDiceBlack, bankroll), currentBet ->
                CrapsUiState(bankroll, currentBet, isDiceBlack)
            }
            //.map {isDiceBlack ->
            //CrapsUiState()

            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed((5_000)),
                initialValue = CrapsUiState()
            )


    //get UI to hold the bet state
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
    val bankroll: StateFlow<Double> = _bankroll

    //function to update isFirstRoll
    fun updateBankRoll(newBalance : Double){
        _bankroll.value = newBalance
    }

    // Ui state for dice images
    private val _isBlack = MutableStateFlow(true)
    val isBlack: StateFlow<Boolean> = _isBlack

    //function to update isBlack
    fun updateIsBlack(isBlackUpdate : Boolean){
        //_isBlack.value = isBlackUpdate
        viewModelScope.launch {
            userPreferencesRepository.saveDicePreference(isBlackUpdate)
        }
    }

    fun getDiceImages(isBlack:Boolean): Pair<Int, Int> {
        val diceItem = DiceItem(resultDie1 = 6, resultDie2 = 1, isBlack)
        return diceItem.getImageResources()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CrapsGameApplication)
                CrapsGameViewModel(application.userPreferencesRepository)
            }
        }
    }

}