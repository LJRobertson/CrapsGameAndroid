package com.example.crapsgame.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.catch
import java.io.IOException

//Repository to save Dice Color Preference in dataStore
class UserPreferencesRepository (
    private val dataStore: DataStore<Preferences>
){
    val isDiceBlack: Flow<Boolean> = dataStore.data
        .catch{ //exception handling
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[IS_DICE_BLACK] ?: true
    }

    private companion object{
        val IS_DICE_BLACK = booleanPreferencesKey("is_dice_black")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun saveDicePreference(isDiceBlack: Boolean){
        dataStore.edit { preferences ->
            preferences[IS_DICE_BLACK] = isDiceBlack
        }
    }

}