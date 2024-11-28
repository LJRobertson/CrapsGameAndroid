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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
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
    crapsGameViewModel: CrapsGameViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    //onPlaceBetButtonClicked: () -> Unit,
    //onHelpButtonClicked: () -> Unit,
    //onPreferencesButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { CrapsGameTopAppBar() },
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
                        navController.navigateUp()}

                )
            }
            composable(route = CrapsGameScreen.Help.name) {
                HelpScreen()
            }
            composable(route = CrapsGameScreen.Preferences.name) {
                PreferencesScreen(
                    viewModel = crapsGameViewModel,
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