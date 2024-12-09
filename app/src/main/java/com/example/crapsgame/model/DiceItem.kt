package com.example.crapsgame.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.crapsgame.R
import com.example.crapsgame.ui.theme.CrapsGameViewModel


//THIS ENTIRE OBJECT IS NOT NEEDED. DICE SELECTION SHOULD BE IN COMPOSABLE

data class DiceItem(
    var resultDie1: Int,
    var resultDie2: Int,
    var isBlack: Boolean,

) {
    //imageResource of each die

    //return a pair of images for the dice based on whether the user
    //preference is set to Red or Black
    fun getImageResources(): Pair<Int, Int> {
        val imageResourceDie1: Int
        val imageResourceDie2: Int
        //isBlack =

        if (isBlack == true) {

            imageResourceDie1 = when (resultDie1) {
                1 -> R.drawable.one_black_310338_1280
                2 -> R.drawable.two_black_310337_1280
                3 -> R.drawable.three_black_310336_1280
                4 -> R.drawable.four_black_310335_1280
                5 -> R.drawable.five_black_310334_1280
                else -> R.drawable.six_black_310333_1280
            }
            imageResourceDie2 = when (resultDie2) {
                1 -> R.drawable.one_black_310338_1280
                2 -> R.drawable.two_black_310337_1280
                3 -> R.drawable.three_black_310336_1280
                4 -> R.drawable.four_black_310335_1280
                5 -> R.drawable.five_black_310334_1280
                else -> R.drawable.six_black_310333_1280
            }
        } else {
            imageResourceDie1 = when (resultDie1) {
                1 -> R.drawable.one_red_152173_1280
                2 -> R.drawable.two_red_152174_1280
                3 -> R.drawable.three_red_152175_1280
                4 -> R.drawable.four_red_152176_1280
                5 -> R.drawable.five_red_152177_1280
                else -> R.drawable.six_red_152178_1280
            }
            imageResourceDie2 = when (resultDie2) {
                1 -> R.drawable.one_black_310338_1280
                2 -> R.drawable.two_black_310337_1280
                3 -> R.drawable.three_black_310336_1280
                4 -> R.drawable.four_black_310335_1280
                5 -> R.drawable.five_black_310334_1280
                else -> R.drawable.six_black_310333_1280
            }
        }
            return Pair(imageResourceDie1, imageResourceDie2)
    }
}