package com.example.crapsgame.ui.theme

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crapsgame.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.round


@Composable
fun PlaceBetScreen(
    viewModel: CrapsGameViewModel,
    onPlaceBetButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val crapsGameState by viewModel.uiState.collectAsState()
    val currentBet by viewModel.currentBet.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp)
                .background(Color.LightGray),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,

            text = stringResource(R.string.place_bet)
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .border(2.dp, Color.DarkGray)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                )
                {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffb31a3f)),
                        border = BorderStroke(2.dp, Color.DarkGray),
                        onClick = {
                            viewModel.updateBet(5.00)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(70.dp)
                            .padding(top = 20.dp)
                    ) {
                        Text(text = stringResource(R.string.five_dollars))
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1229c0)),
                        border = BorderStroke(2.dp, Color.DarkGray),
                        onClick = {
                            viewModel.updateBet(10.00)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(70.dp)
                            .padding(top = 10.dp)
                    ) {
                        Text(text = stringResource(R.string.ten_dollars))
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff0ea41e)),
                        border = BorderStroke(2.dp, Color.DarkGray),
                        onClick = {
                            viewModel.updateBet(25.00)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(70.dp)
                            .padding(top = 10.dp)
                    ) {
                        Text(text = stringResource(R.string.twenty_five_dollars))
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff272a30)),
                        border = BorderStroke(2.dp, Color.DarkGray),
                        onClick = {
                            viewModel.updateBet(100.00)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(70.dp)
                            .padding(top = 10.dp)
                    ) {
                        Text(text = stringResource(R.string.one_hundred_dollars))
                    }

                    //Text area to Display Bet Amount
                    Text(
                        modifier = Modifier,

                        style = MaterialTheme.typography.displaySmall,
                        text = stringResource(R.string.amount_bet) + currentBet
                    )
                    Button(
                        border = BorderStroke(2.dp, Color.DarkGray),
                        onClick = onPlaceBetButtonClicked ,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(70.dp)
                            .padding(top = 10.dp, bottom = 20.dp)
                    ) {
                        Text(text = stringResource(R.string.place_bet))
                    }
                }
            }
        }
    }
}


/*fun placeBetButtonClick(betAmount: Int){
    crapsGameViewModel: CrapsGameViewModel = viewModel(),

    val crapsGameState by crapsGameViewModel.uiState.collectAsState()
}*/

@Preview
@Composable
fun PlaceBetScreenPreview() {
    val viewModel = CrapsGameViewModel()
    PlaceBetScreen(
        viewModel = viewModel,
        onPlaceBetButtonClicked = {}
    )
}