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


enum class CrapsGameScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    PlaceBet(title = R.string.place_bet),
    Help(title = R.string.help_screen),
    Preferences(title = R.string.preferences)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrapsGameTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = MaterialTheme.typography.displayLarge,
                    text = stringResource(R.string.app_name)
                )
            }
        },
        modifier = modifier
    )
}

//layout the game screen
@Composable
fun CrapsGameApp(
    navController: NavHostController = rememberNavController(),
    //onPlaceBetButtonClicked: () -> Unit,
    //onHelpButtonClicked: () -> Unit,
    //onPreferencesButtonClicked: () -> Unit,
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
        topBar = { CrapsGameTopAppBar() },
        modifier = Modifier
    ) { it ->
        //val uiState by viewModel.uiState.collectAsState()
        //add NavHost
        NavHost(
            navController = navController,
            startDestination = CrapsGameScreen.Start.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = CrapsGameScreen.Start.name) {
                GameScreen(
                    onPlaceBetButtonClicked = {navController.navigate(CrapsGameScreen.PlaceBet.name)},
                    onHelpButtonClicked = {navController.navigate(CrapsGameScreen.Help.name)},
                    onPreferencesButtonClicked = {navController.navigate(CrapsGameScreen.Preferences.name)},
                )
                //modifier = Modifier
                //.fillMaxSize()
                //.padding()
            }
            composable(route = CrapsGameScreen.PlaceBet.name) {
                //val context = LocalContext.current
                //call PlaceBet Screen
                PlaceBetScreen(
                    //TODO: pass in and out bankroll
                    //bankRoll = uiState.bankRoll,
                )
            }
            composable(route = CrapsGameScreen.Help.name) {
                HelpScreen()
                //TODO:
            }
            composable(route = CrapsGameScreen.Preferences.name) {
                PreferencesScreen()
                //TODO:
            }
        }
    }
}




@Preview
@Composable
fun CrapsGamePreview() {
    CrapsGameApp(
        //onPlaceBetButtonClicked = {},
        //onHelpButtonClicked = {},
        //onPreferencesButtonClicked = {}
    )
}