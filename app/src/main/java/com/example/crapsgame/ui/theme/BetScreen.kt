package com.example.crapsgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.crapsgame.R


@Composable
fun PlaceBetScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
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
        Button(
            onClick = {
                //TODO
            },
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(70.dp)
                .padding(top = 10.dp)
        ) {
            Text(text = stringResource(R.string.five_dollars))
        }
        Button(
            onClick = {
                //TODO
            },
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(70.dp)
                .padding(top = 10.dp)
        ) {
            Text(text = stringResource(R.string.ten_dollars))
        }
        Button(
            onClick = {
                //TODO
            },
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(70.dp)
                .padding(top = 10.dp)
        ) {
            Text(text = stringResource(R.string.twenty_five_dollars))
        }
        Button(
            onClick = {
                //TODO
            },
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(70.dp)
                .padding(top = 10.dp)
        ) {
            Text(text = stringResource(R.string.one_hundred_dollars))
        }
    }
}


@Preview
@Composable
fun PlaceBetScreenPreview() {
    PlaceBetScreen()
}