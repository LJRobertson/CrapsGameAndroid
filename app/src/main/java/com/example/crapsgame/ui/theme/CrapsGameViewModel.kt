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
import com.example.crapsgame.R
import com.example.crapsgame.data.UserPreferencesRepository
//import com.example.crapsgame.model.DiceItem
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
    private val _dieImage1 = MutableStateFlow(R.drawable.one_black_310338_1280)
    private val _dieImage2 = MutableStateFlow(R.drawable.six_black_310333_1280)


    //private val _uiState = MutableStateFlow(CrapsUiState(isDiceBlack = false))
    //val uiState: StateFlow<CrapsUiState> = _uiState.asStateFlow()
    val uiState: StateFlow<CrapsUiState> =
        userPreferencesRepository.isDiceBlack
            .combine(_bankroll) {isDiceBlack, bankroll ->
                isDiceBlack to bankroll
            }
            .combine(_currentBet) { (isDiceBlack, bankroll), currentBet ->
                CrapsUiState(bankroll, currentBet, isDiceBlack, currentPoint.value)
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

    //update the bankroll
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

/*    fun getDiceImages(isBlack:Boolean): Pair<Int, Int> {
        val diceItem = DiceItem(resultDie1 = 6, resultDie2 = 1, isBlack)
        return diceItem.getImageResources()
    }*/

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CrapsGameApplication)
                CrapsGameViewModel(application.userPreferencesRepository)
            }
        }
    }

    //Ui to hold dice image
    val dieImage1: StateFlow<Int> = _dieImage1
    val dieImage2: StateFlow<Int> = _dieImage2

    //function to update image
    fun updateDiceImages(die1: Int, die2: Int) {
        //val isBlack = userPreferencesRepository.isDiceBlack.value
        val isBlack = uiState.value.isDiceBlack

        _dieImage1.value = getDiceImage(die1, isBlack)
        _dieImage2.value = getDiceImage(die2, isBlack)
    }


    fun getDiceImage(dieResult: Int, isBlack:Boolean): Int {

        return if (isBlack == true) {

            when (dieResult) {
                1 -> R.drawable.one_black_310338_1280
                2 -> R.drawable.two_black_310337_1280
                3 -> R.drawable.three_black_310336_1280
                4 -> R.drawable.four_black_310335_1280
                5 -> R.drawable.five_black_310334_1280
                else -> R.drawable.six_black_310333_1280
            }

        } else {
            when (dieResult) {
                1 -> R.drawable.one_red_152173_1280
                2 -> R.drawable.two_red_152174_1280
                3 -> R.drawable.three_red_152175_1280
                4 -> R.drawable.four_red_152176_1280
                5 -> R.drawable.five_red_152177_1280
                else -> R.drawable.six_red_152178_1280
            }
        }
    }
}