package com.example.crapsgame.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.security.KeyStore.TrustedCertificateEntry
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.crapsgame.ui.theme.PlaceBetScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.dimensionResource
import com.example.crapsgame.ui.theme.HelpScreen
import com.example.crapsgame.ui.theme.PreferencesScreen


//layout the game screen
@Composable
fun GameScreen(
    onPlaceBetButtonClicked: () -> Unit,
    onHelpButtonClicked: () -> Unit,
    onPreferencesButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    //dice always show 1 and 6
    var resultDie1 by remember { mutableStateOf(1) }
    var resultDie2 by remember { mutableStateOf(6) }
    //imageResource of each die
    val imageResourceDie1 = when (resultDie1) {
        1 -> R.drawable.one_black_310338_1280
        2 -> R.drawable.two_black_310337_1280
        3 -> R.drawable.three_black_310336_1280
        4 -> R.drawable.four_black_310335_1280
        5 -> R.drawable.five_black_310334_1280
        else -> R.drawable.six_black_310333_1280
    }
    val imageResourceDie2 = when (resultDie2) {
        1 -> R.drawable.one_black_310338_1280
        2 -> R.drawable.two_black_310337_1280
        3 -> R.drawable.three_black_310336_1280
        4 -> R.drawable.four_black_310335_1280
        5 -> R.drawable.five_black_310334_1280
        else -> R.drawable.six_black_310333_1280
    }
    var bankrollBalance by remember { mutableDoubleStateOf(100.00) }
    var isPointSet by remember { mutableStateOf(false) }
    var point by remember { mutableStateOf<Int?>(null) }

    var totalRoll: Int
    var isFirstRoll by remember { mutableStateOf(true) }
    var placedBet by remember { mutableDoubleStateOf(5.00) }
    var amountWon by remember { mutableDoubleStateOf(0.00) }
    Scaffold(
        //topBar = { CrapsGameTopAppBar() },
        modifier = Modifier
    ) { it ->
        //val uiState by viewModel.uiState.collectAsState()
        //add NavHost
                Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            //verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //Add space after top bar
            //Spacer(modifier = Modifier.weight(0.5f))
            //point row
            Row(
                modifier = modifier
                    .padding(0.dp)
            ) {
                if (isPointSet == true) {
                    Text(
                        text = stringResource(R.string.point_set) + " $point"
                    )
                }
            }
            //Spacer(modifier = Modifier.height(20.dp))
            //Row to hold the dice
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .padding(bottom = 35.dp)
                    .fillMaxWidth(),
                //.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                //verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(imageResourceDie1),
                    contentDescription = resultDie1.toString(),
                    modifier = Modifier
                        .height(100.dp)
                )
                //add some space between the dice
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(imageResourceDie2),
                    contentDescription = resultDie2.toString(),
                    modifier = Modifier
                        .height(100.dp)
                )
            }
            //Spacer(modifier = Modifier.height(10.dp))
            //row to hold the button
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                //verticalAlignment = Alignment.CenterVertically
                //.weight(1f),
                //horizontalArrangement = Arrangement.spacedBy(20.dp)
                //.fillMaxWidth()
                //.padding(innerPadding),
            ) {

                Button(
                    onClick = {
                        //roll the dice
                        resultDie1 = (1..6).random()
                        resultDie2 = (1..6).random()

                        totalRoll = resultDie1 + resultDie2

                        //check for point
                        if (isPointSet == false) {
                            point = (DeterminePointSet(totalRoll))
                            if (point != null) {
                                isPointSet = true
                            }
                        }

                        //run the game
                        amountWon = RunCrapsGame(totalRoll, isFirstRoll, placedBet, point)
                        //update bankroll
                        bankrollBalance = bankrollBalance + amountWon

                        //if game is won rest the roll
                        if (isPointSet == true && totalRoll == point) {
                            isFirstRoll = true
                            isPointSet = false
                        }
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
                //verticalAlignment = Alignment.CenterVertically

                //.weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.amount_won) + "$amountWon"
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
                    text = stringResource(R.string.bankroll)
                )
                Text(
                    text = bankrollBalance.toString()
                )
            }
            Row(
                modifier = modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                //verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        onPlaceBetButtonClicked()
                        //navController.navigate(CrapsGameScreen.PlaceBet.name)
                    },
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
                ){
                    Text(text = stringResource(R.string.help_screen))
                }

                Spacer(modifier= Modifier.padding(start = 15.dp))

                //Preferences Button
                Button(
                    onClick = { //navController.navigate(CrapsGameScreen.Preferences.name)
                        onPreferencesButtonClicked()
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(text = stringResource(R.string.preferences))
                }
            }
        }
    }
}

fun RunCrapsGame(
    diceTotal: Int,
    isFirstRole: Boolean,
    currentBet: Double,
    gamePoint: Int?
): Double {
    var pointSet: Int?
    var amountWon: Double = 0.00

    if (isFirstRole == true) {
        amountWon = FirstRoundPayout(diceTotal, currentBet)
        DeterminePointSet(diceTotal)

    }
    if (gamePoint != null) {
        pointSet = gamePoint
        amountWon = SubsequentRoundPayout(diceTotal, currentBet, pointSet)
    }
    return amountWon
}

fun DeterminePointSet(totalRoll: Int): Int? {
    val nonPointNumbers = setOf(2, 3, 7, 11, 12)
    var pointNumber: Int

    if (totalRoll in nonPointNumbers) {
        return null
    } else {
        pointNumber = totalRoll
        return pointNumber
    }
}

fun FirstRoundPayout(totalRoll: Int, currentBet: Double): Double {
    val winningNumbers = setOf(7, 11)
    val losingNumbers = setOf(2, 3, 12)
    var payout: Double

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

fun SubsequentRoundPayout(totalRoll: Int, currentBet: Double, gamePoint: Int): Double {
    var payout: Double = 0.00
    var units: Double

    if (totalRoll == 7) {
        payout = 0 - currentBet // loses bet
    } else if (totalRoll == gamePoint) {
        if (gamePoint == 4 || gamePoint == 10) {
            //payout is 9:5
            units = currentBet // 5
            payout = units * 9
            return payout
        } else if (gamePoint == 5 || gamePoint == 9) {
            //payout is 7:5
            units = currentBet // 5
            payout = units * 7
            return payout
        } else if (gamePoint == 6 || gamePoint == 8) {
            //payout is 6:7
            units = currentBet // 6
            payout = units * 7
            return payout
        }
    } else {
        //nothing won or lost
        payout = 0.00
    }
    return payout
}

//logic for PlaceBet button
private fun navigateToPlaceBet(
    viewModel: CrapsGameViewModel,
    navController: NavHostController
) {
    navController.popBackStack(CrapsGameScreen.PlaceBet.name, inclusive = false)
}

fun onPlaceBetButtonClicked() {
    //TODO
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(
        onPlaceBetButtonClicked = {},
        onHelpButtonClicked = {},
        onPreferencesButtonClicked = {}
    )
}