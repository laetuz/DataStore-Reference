package com.neotica.datastorereference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Step 9: Create SettingPreferences, add DataStore constructor and apply singleton pattern
//Step 10: Add a private constructor
//Step 11: Create a new parameter that contains DataStore with Preferences as its type.
class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {
    //Step 12: Create a companion object
    companion object {
        //Step 13: Annotate Volatile
        @Volatile
        //Step 14: Declare INSTANCE with SettingPreferences as its type. Make it nullable
        private var INSTANCE: SettingPreferences? = null

        //Step 15: Create a function to get INSTANCE
        //Step 16: take the parameter from datastore of type DataStore<Preferences> and returns an
        //instance of the SettingPreferences class.
        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            //Step 17: If instance is null, then execute synchronized block.
            return INSTANCE ?: synchronized(this) {
                //Step 18: If instance is not null, then returns the existing instance of SettingPreferences
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    //Step 19: Declare a new boolean preferences key with THEME_KEY as the variable
    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    //Step 20: Create a function that will get the preference value
    //Step 20.1: U
    fun getThemeSetting(): Flow<Boolean> {
        //Step 20.2
        return dataStore.data.map { preferences ->
            //Step 20.3
            preferences[THEME_KEY] ?:false
        }
    }

    //Step 21
    suspend fun saveThemeSetting(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkMode
        }
    }
}