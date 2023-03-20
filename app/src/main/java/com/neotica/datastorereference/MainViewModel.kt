package com.neotica.datastorereference

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//Step 22: Create a new ViewModel class
//Step 23: Create a parameter that calls SettingPreferences, Extend to ViewModel
class MainViewModel(private val pref: SettingPreferences) : ViewModel() {
    //Step 24: Create a function that extends from LiveData<Boolean>
    fun getThemeSettings(): LiveData<Boolean> {
        //Step 25: Return to SettingPreferences and call getThemeSetting as Live Data
        return pref.getThemeSetting().asLiveData()
    }

    //Step 26
    fun saveThemeSettings(isDarkMode: Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkMode)
        }
    }
}