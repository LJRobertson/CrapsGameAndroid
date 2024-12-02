package com.example.crapsgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.crapsgame.R


@Composable
fun PreferencesScreen(
    viewModel: CrapsGameViewModel,
    modifier: Modifier = Modifier,
) {
    var diceColorOption = remember { mutableStateOf("Red") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp)
                .background(Color.LightGray),
            textAlign = TextAlign.Center,

            style = MaterialTheme.typography.displayMedium,
            text = stringResource(R.string.preferences)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .fillMaxHeight(0.75f)
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displaySmall,
                    text = stringResource(R.string.dice_color)
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.red));
                RadioButton(
                    selected = diceColorOption.value == "Red",
                    onClick = { //TODO: click functionality
                    },
                )
                Spacer(
                    modifier = modifier
                        .padding(horizontal = 15.dp)
                )
                Text(text = stringResource(R.string.black));
                RadioButton(
                    selected = diceColorOption.value == "Black",
                    onClick = { //TODO: click functionality
                    },
                )
            }
            Row(
                modifier = modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = {
                        //TODO
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(70.dp)
                        .padding(top = 10.dp),
                ) {
                    Text(text = stringResource(R.string.save_preferences))
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun PreferencesScreenPreview() {
    val viewModel = CrapsGameViewModel()
    PreferencesScreen(
        viewModel = viewModel
    )
}*/
