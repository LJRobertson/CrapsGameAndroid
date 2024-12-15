package com.example.crapsgame.ui.theme

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import com.example.crapsgame.R
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState


enum class CrapsGameScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    PlaceBet(title = R.string.place_bet),
    Help(title = R.string.help_screen),
    Preferences(title = R.string.preferences)
}
//composables for main application
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrapsGameTopAppBar(
    currentScreen: CrapsGameScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

//layout the game screen
@Composable
fun CrapsGameApp(
    crapsGameViewModel: CrapsGameViewModel = viewModel(factory = CrapsGameViewModel.Factory),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CrapsGameScreen.valueOf(
        backStackEntry?.destination?.route ?: CrapsGameScreen.Start.name
    )

    Scaffold(
        topBar = { CrapsGameTopAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp()}
        ) },
        modifier = Modifier
    ) { it ->
        val uiState by crapsGameViewModel.uiState.collectAsState()


        //add NavHost
        NavHost(
            navController = navController,
            startDestination = CrapsGameScreen.Start.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = CrapsGameScreen.Start.name) {
                GameScreen(
                    viewModel = crapsGameViewModel,
                    onPlaceBetButtonClicked = {navController.navigate(CrapsGameScreen.PlaceBet.name)},
                    onHelpButtonClicked = {navController.navigate(CrapsGameScreen.Help.name)},
                    onPreferencesButtonClicked = {navController.navigate(CrapsGameScreen.Preferences.name)},
                )
            }
            composable(route = CrapsGameScreen.PlaceBet.name) {
                //call PlaceBet Screen
                PlaceBetScreen(
                    viewModel = crapsGameViewModel,
                    onPlaceBetButtonClicked = {
                        var newBalance = (crapsGameViewModel.bankroll.value - crapsGameViewModel.currentBet.value)
                        crapsGameViewModel.updateBankRoll(newBalance)
                        navController.navigateUp()}
                )
            }
            composable(route = CrapsGameScreen.Help.name) {
                HelpScreen()
            }
            composable(route = CrapsGameScreen.Preferences.name) {
                PreferencesScreen(
                    crapsGameViewModel = crapsGameViewModel,
                    onSaveButtonClicked = {
                        navController.popBackStack(CrapsGameScreen.Start.name,inclusive = false)
                    }
                )
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