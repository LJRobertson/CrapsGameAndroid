package com.example.crapsgame

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import com.example.crapsgame.data.UserPreferencesRepository


private const val DICE_PREFERENCE_NAME = "dice_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DICE_PREFERENCE_NAME
)

class CrapsGameApplication : Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super .onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}