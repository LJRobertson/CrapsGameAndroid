package com.example.crapsgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun HelpScreen(
    modifier: Modifier = Modifier,
) {
    var diceColorOption = remember { mutableStateOf("Red") }
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp)
                .background(Color.LightGray),
            textAlign = TextAlign.Center,

            style = MaterialTheme.typography.displayMedium,
            text = stringResource(R.string.help_screen)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .fillMaxHeight()
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp)
                    .border(2.dp, Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        text = stringResource(R.string.how_to_play)
                    )


                    Row(
                        //horizontalArrangement = Arrangement.,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Text(
                            //How to Play
                            text = "Craps is a dice based gambling game." +
                                    "\n\nPlace a bet by selecting the Place Bet button. You may choose $5, $10, $25 or $100." +
                                    "\n\nOn the initial roll (while no Point is set) a roll of a 7 or 11 wins. A roll of 2, 3, or 12 " +
                                    "will lose the bet amount and a new bet may be made. Any other number will set the Point." +
                                    "\n\nOnce a Point has been set no additional bets may be placed until the round is won or" +
                                    " lost. A round is lost on a roll of 7. A round is won on a roll that matches the set Point. When" +
                                    "a round is won, winnings are paid out according to the Odds below."
                        );

                    }
                    Row(
                        //horizontalArrangement = Arrangement.,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) { //ODDS
                        Text(
                            style = MaterialTheme.typography.labelSmall,
                            text = stringResource(R.string.odds)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start =20.dp),
                    ) {
                        Text(
                            text = "4 or 10: Pays 2:1" +
                                    "\n5 or 9: Pays 3:2" +
                                    "\n6 or 8: Pays 6:5"
                        )
                    }
                    Row( // Preferences
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .padding(top = 8.dp),
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelSmall,
                            text = stringResource(R.string.preferences)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start =20.dp),
                    ) {
                        Text(
                            text = "The preferences screen allow you to select your dice option." +
                                    "Options are dice with red pips or dice with black pips."
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun HelpScreenPreview() {
    HelpScreen()
}