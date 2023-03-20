package com.neotica.datastorereference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//Step 27: Create a new ViewModelFactory class
//Step 28: Create a parameter to SettingPreferences
class ViewModelFactory(private val pref: SettingPreferences) :
    //Step 29: Extend to ViewModelProvider and call NewInstanceFactory
    ViewModelProvider.NewInstanceFactory() {
    //Step 30: Suppress Unchecked cast
    @Suppress("UNCHECKED_CAST")
    //Step 31: override view model exception method
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Step 32: Create condition if
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            //Step 33: Return ViewModel as T with preference as the parameter
            return MainViewModel(pref) as T
        }
        //Step 34: Throw Illegal argument exception
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass.name)
    }
}