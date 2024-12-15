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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.round
import androidx.compose.runtime.remember


//layout the game screen
@Composable
fun GameScreen(
    viewModel: CrapsGameViewModel = viewModel(),
    onPlaceBetButtonClicked: () -> Unit,
    onHelpButtonClicked: () -> Unit,
    onPreferencesButtonClicked: () -> Unit,
    //onSaveButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    //val crapsGameUiState by viewModel.uiState.collectAsState()
    var imageDie1 by rememberSaveable { mutableStateOf(R.drawable.one_black_310338_1280) }
    var imageDie2 by rememberSaveable { mutableStateOf(R.drawable.six_black_310333_1280) }
    val dieImage1 by viewModel.dieImage1.collectAsState()
    val dieImage2 by viewModel.dieImage2.collectAsState()
    val bankrollBalance by viewModel.bankroll.collectAsState()
    val isBlack by viewModel.isBlack.collectAsState()
    val amountWon by viewModel.amountWon.collectAsState()
    val currentBet by viewModel.currentBet.collectAsState()
    val currentPoint by viewModel.currentPoint.collectAsState()
    var isCrapsGameRunning by remember {mutableStateOf(false)}
+
    //LaunchedEffect to pick up whether the dice have been changed between black and red images
    LaunchedEffect (isBlack){
        viewModel.updateDiceImages(dieImage1,dieImage2)
    }

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
                    painter = painterResource(dieImage1),
                    contentDescription = imageDie1.toString(),
                    modifier = Modifier
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))

                Image(
                    painter = painterResource(id=dieImage2),
                    contentDescription = imageDie2.toString(),
                    modifier = Modifier
                        .height(100.dp)
                )
            }

            //row to hold the Roll button
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {

                Button(
                    onClick = {
                        //run craps game -- using Bool because you cannot call a composable function from here.
                        if (isCrapsGameRunning == false) {
                            isCrapsGameRunning = true
                        }
                    },
                ) {
                    Text(text = stringResource(R.string.roll))
                }
                //run the game from this point
                if (isCrapsGameRunning == true){
                    RunCrapsGame(viewModel)
                    //change isCrapsGameRunning flag to allow game to be run again on next button click
                    isCrapsGameRunning = false
                }
            }
            //row to hold the buttons
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
            ) {
                Text(
                    text = stringResource(R.string.amount_bet) + " " + String.format("%.2f", currentBet)
                )
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


//Roll singular die
fun rollDie(): Int {
    val dieResult = (1..6).random()

    return dieResult
    }

@Composable
fun RunCrapsGame(viewModel: CrapsGameViewModel
    ) {
        //Runs a round of the game

    var newBankroll: Double
    val amountWon by viewModel.amountWon.collectAsState()
    val currentBet by viewModel.currentBet.collectAsState()
    val currentPoint by viewModel.currentPoint.collectAsState()
    val firstRoll by viewModel.isFirstRoll.collectAsState()
    var possiblePoint: Int
    val bankrollBalance by viewModel.bankroll.collectAsState()
    var payoutAmount = 0.00

    //roll the dice
        val resultDie1 = rollDie()
        val resultDie2 = rollDie()
        val totalRoll = resultDie1 + resultDie2

       //update the images
        viewModel.updateDiceImages(resultDie1,resultDie2)

       //check for point
        if (currentPoint != 0) {
            viewModel.updateIsPointSet(true)
        }
        //first roll has different logic
        if (firstRoll == true) {
            //calculate first round payout
            payoutAmount = firstRoundPayout(totalRoll, currentBet)
            //set game point if applicable
            possiblePoint = determinePointSet(totalRoll)
            if (possiblePoint != 0) {
                viewModel.updatePoint(possiblePoint)
                viewModel.updateIsPointSet(true)
                viewModel.updateFirstRoll(false)
            }
        }

        //if there is already a point set
        if (currentPoint != 0) {
            //payouts for when a game point is set
            payoutAmount = subsequentRoundPayout(totalRoll, currentBet, currentPoint, viewModel)
            //update bankroll
            newBankroll = bankrollBalance + amountWon
            viewModel.updateBankRoll(newBankroll)
        }

    //update win amount
    viewModel.updateAmountWon(payoutAmount)
    //update bankroll
    newBankroll = bankrollBalance + amountWon
    viewModel.updateBankRoll(newBankroll)
}

fun determinePointSet(totalRoll: Int): Int {
    //determines if a game point can be set
    val nonPointNumbers = setOf(2, 3, 7, 11, 12)
    val pointNumber: Int

    //if the total roll is not in the nonPointNumbers, set it to the game point
    if (totalRoll in nonPointNumbers) {
        return 0
    } else {
        pointNumber = totalRoll
        return pointNumber
    }
}

fun firstRoundPayout(totalRoll: Int, currentBet: Double): Double {
    //payout logic for first round of gameplay
    val winningNumbers = setOf(7, 11)
    val losingNumbers = setOf(2, 3, 12)
    val payout: Double

    //if the total roll is a losing number, current bet amount is lost
    if (totalRoll in losingNumbers) {
        payout = 0.00 - currentBet //loses bet
        return payout
    // if total roll is in winning numbers, the payout is twice the bet amount
    } else if (totalRoll in winningNumbers) {
        payout = currentBet * 1 // pays even money
        return payout
    //numbers not in winning or losing numbers do not earn a payout.
    } else {
        payout = 0.00
        return payout
    }
}

fun subsequentRoundPayout(totalRoll: Int, currentBet: Double, gamePoint: Int,     viewModel: CrapsGameViewModel
): Double {
    //payout logic for when a game point has been set
    var payout = 0.00
    var units: Double

    //a roll of 7 always loses
    if (totalRoll == 7) {
        payout = 0 - currentBet // loses bet
        viewModel.updateFirstRoll(true)
        viewModel.updatePoint(0)
        viewModel.updateIsPointSet(false)
    // a roll that matches the game point wins
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
    // everything else continues without a payout
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
