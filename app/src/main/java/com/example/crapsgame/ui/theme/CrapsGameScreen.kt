package com.example.crapsgame.ui.theme

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrapsGameTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
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
    modifier: Modifier = Modifier
) {
    //dice always show 1
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
    var bankrollBalance by remember {mutableStateOf(100.00)}
    var isPointSet: Boolean = true
    var point: Int = 3
    var totalRoll = resultDie1 + resultDie2
    var isFirstRoll: Boolean = false

    //TODO: AppBar pass in
    Scaffold(
        topBar = { CrapsGameTopAppBar() },
    ) { it ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            //Add space after top bar
            //Spacer(modifier = Modifier.weight(0.5f))
            //point row
            Row(
                modifier = modifier
            ){
                if (isPointSet == true){
                    Text(
                        text = stringResource(R.string.point_set) +" $point"
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            //Row to hold the dice
            Row(

                modifier = modifier
                    .fillMaxWidth(),
                //.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
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
            Spacer(modifier = Modifier.height(40.dp))
            //row to hold the button
            Row(

                modifier = modifier,
                    //.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
                //.fillMaxWidth()
                //.padding(innerPadding),
            ) {

                Button(
                    onClick = {
                        resultDie1 = (1..6).random()
                        resultDie2 = (1..6).random()
                    },
                ) {
                    Text(text = stringResource(R.string.roll))
                }
            }
            //Spacer(modifier = Modifier.height(15.dp))
            //row to hold the button
            Row(
                modifier = modifier
                //.weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.bankroll)
                )
                Text(
                    text = bankrollBalance.toString()
                )
            }
        }
    }
}


fun FirstRoundPayout(totalRoll: Int){


}

@Preview
@Composable
fun CrapsGamePreview() {
    CrapsGameApp()
}