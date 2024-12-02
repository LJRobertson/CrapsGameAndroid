package com.example.crapsgame.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import com.example.crapsgame.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crapsgame.model.DiceItem
import java.text.NumberFormat
import kotlin.math.round


//layout the game screen
@Composable
fun GameScreen(
    viewModel: CrapsGameViewModel,
    onPlaceBetButtonClicked: () -> Unit,
    onHelpButtonClicked: () -> Unit,
    onPreferencesButtonClicked: () -> Unit,
    //onSaveButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val crapsGameUiState by viewModel.uiState.collectAsState()
    //dice always show 1 and 6 to start
    var imageDie1 by rememberSaveable { mutableStateOf(R.drawable.one_black_310338_1280) }
    var imageDie2 by rememberSaveable { mutableStateOf(R.drawable.six_black_310333_1280) }
    var resultDie1 by rememberSaveable { mutableStateOf(1) }
    var resultDie2 by rememberSaveable { mutableStateOf(6) }
    //imageResource of each die
    //Moved to DiceItem

    val bankrollBalance by viewModel.bankroll.collectAsState()
    val isPointSet by viewModel.isPointSet.collectAsState()
    //val isBlack by rememberSaveable {mutableStateOf(true)}
    val isBlack by viewModel.isBlack.collectAsState()
    var newBankroll: Double
    var totalRoll: Int
    val isFirstRoll by viewModel.isFirstRoll.collectAsState()

    val amountWon by viewModel.amountWon.collectAsState()
    val currentBet by viewModel.currentBet.collectAsState()
    val currentPoint by viewModel.currentPoint.collectAsState()

    Scaffold(
        modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //message row
            Row(
                modifier = modifier
                    .padding(bottom = 15.dp)
            ) {
                if (currentPoint != 0) {
                    Text(
                        style = MaterialTheme.typography.displayMedium,
                        text = stringResource(R.string.point_set) + " " + "$currentPoint"
                    )
                }

                if (amountWon > 0) {
                    Text(
                        style = MaterialTheme.typography.displayMedium,
                        text = stringResource(R.string.amount_won) + " " + String.format("%.2f", amountWon)
                    )
                }
                if (amountWon < 0) {
                    Text(
                        style = MaterialTheme.typography.displayMedium,
                        text = stringResource(R.string.amount_lost) + " " + String.format("%.2f", amountWon)
                    )
                }
            }
            //Row to hold the dice
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .padding(bottom = 35.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(imageDie1),
                    contentDescription = imageDie1.toString(),
                    modifier = Modifier
                        .height(100.dp)
                )
                //add some space between the dice
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(imageDie2),
                    contentDescription = imageDie2.toString(),
                    modifier = Modifier
                        .height(100.dp)
                )
            }

            //row to hold the button
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {

                Button(
                    onClick = {
                        //roll the dice
                        val (totalRoll, diceItem) = rollDice(isBlack)

                        //get the total and new images back
                        val (newImageDie1, newImageDie2) = diceItem.getImageResources()
                        //store the new images over the old
                        imageDie1 = newImageDie1
                        imageDie2 = newImageDie2

                        //run a round of the game
                        var winAmount = runCrapsGame(
                            totalRoll,
                            isFirstRoll,
                            currentBet,
                            currentPoint,
                            viewModel
                        )

                        viewModel.updateAmountWon(winAmount)

                        //check for point
                            if (currentPoint != 0) {
                                viewModel.updateIsPointSet(true)
                        }

                        //update bankroll
                        newBankroll = bankrollBalance + amountWon
                        viewModel.updateBankRoll(newBankroll)

//                        //if game is won reset the roll
//                        if (isPointSet == true && totalRoll == currentPoint) {
//                            viewModel.updateFirstRoll(true)
//                            viewModel.updateIsPointSet(false)
//                        }
                    },
                ) {
                    Text(text = stringResource(R.string.roll))
                }
            }
            //Spacer(modifier = Modifier.height(10.dp))
            //row to hold the button
           Row(
                modifier = modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                ) {
                Text(
                    text = stringResource(R.string.amount_won) + " " + String.format("%.2f", amountWon)
                )
            }
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                //verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.amount_bet) + " " + String.format("%.2f", currentBet)
                )
                //Text(
               //     text = currentBet.toString()
                //)
            }
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.bankroll) + " " + String.format("%.2f", bankrollBalance)
                )
            }
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        onPlaceBetButtonClicked()
                    },
                    //disable button once a point has been set
                    enabled = currentPoint == 0
                ) {
                    Text(text = stringResource(R.string.place_bet))
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Row(
                modifier = Modifier
            ) {
                //Help Button
                Button(
                    onClick = { //navController.navigate((CrapsGameScreen.Help.name))
                        onHelpButtonClicked()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.50f)
                ) {
                    Text(text = stringResource(R.string.help_screen))
                }

                Spacer(modifier = Modifier.padding(start = 15.dp))

                //Preferences Button
                Button(
                    //if a gamePoint has been set, disable the option to place a bet
                    //enabled = currentPoint <= 0,
                    onClick = {
                        onPreferencesButtonClicked()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.preferences))
                }
            }
        }
    }
}


//Roll Dice -- happens when Roll is clicked
fun rollDice(areDiceBlack: Boolean): Pair<Int, DiceItem> {
    val resultDie1 = (1..6).random()
    val resultDie2 = (1..6).random()
    val diceItem = DiceItem(resultDie1, resultDie2, areDiceBlack)
    val totalRoll = resultDie1 + resultDie2
    return Pair(totalRoll, diceItem)
}

fun runCrapsGame(
    diceTotal: Int,
    isFirstRole: Boolean,
    currentBet: Double,
    gamePoint: Int,
    viewModel: CrapsGameViewModel
): Double {
    //var currentPoint: Int
    var amountWon: Double = 0.00
    var possiblePoint: Int

    if (isFirstRole == true) {
        amountWon = firstRoundPayout(diceTotal, currentBet)
        possiblePoint = determinePointSet(diceTotal)
        if (possiblePoint != 0) {
            viewModel.updatePoint(possiblePoint)
            viewModel.updateIsPointSet(true)
            viewModel.updateFirstRoll(false)
        }
    }
    if (gamePoint != 0) {
        //currentPoint may not be needed here since I can use gamePoint directly
        // currentPoint = gamePoint
        amountWon = subsequentRoundPayout(diceTotal, currentBet, gamePoint, viewModel)
    }
    return amountWon
}

fun determinePointSet(totalRoll: Int): Int {
    val nonPointNumbers = setOf(2, 3, 7, 11, 12)
    val pointNumber: Int

    if (totalRoll in nonPointNumbers) {
        return 0
    } else {
        pointNumber = totalRoll
        return pointNumber
    }
}

fun firstRoundPayout(totalRoll: Int, currentBet: Double): Double {
    val winningNumbers = setOf(7, 11)
    val losingNumbers = setOf(2, 3, 12)
    val payout: Double

    if (totalRoll in losingNumbers) {
        payout = 0.00 - currentBet //loses bet
        return payout
    } else if (totalRoll in winningNumbers) {
        payout = currentBet * 2 // pays double
        return payout
    } else {
        payout = 0.00
        return payout
    }
}

fun subsequentRoundPayout(totalRoll: Int, currentBet: Double, gamePoint: Int,     viewModel: CrapsGameViewModel
): Double {
    var payout = 0.00
    var units: Double

    if (totalRoll == 7) {
        payout = 0 - currentBet // loses bet
        viewModel.updateFirstRoll(true)
        viewModel.updatePoint(0)
        viewModel.updateIsPointSet(false)
    } else if (totalRoll == gamePoint) {
        if (gamePoint == 4 || gamePoint == 10) {
            //payout is 9:5
            units = currentBet / 5
            units = round(units)
            payout = units * 9
            //return payout
        } else if (gamePoint == 5 || gamePoint == 9) {
            //payout is 7:5
            units = currentBet / 5
            units = round(units)
            payout = units * 7
            //return payout
        } else if (gamePoint == 6 || gamePoint == 8) {
            //payout is 6:7
            units = currentBet / 6
            units = round(units)
            payout = units * 7
            //return payout
        }
        //reset Point and first roll
        viewModel.updateFirstRoll(true)
        viewModel.updatePoint(0)
        viewModel.updateIsPointSet(false)
        return payout
    } else {
        //nothing won or lost
        payout = 0.00
    }
    return payout
}


/*
@Preview
@Composable
fun GameScreenPreview() {
    val viewModel = CrapsGameViewModel()
    GameScreen(
        viewModel = viewModel,
        onPlaceBetButtonClicked = {},
        onHelpButtonClicked = {},
        onPreferencesButtonClicked = {}
    )
}*/
