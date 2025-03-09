package com.example.cybercigarette

import android.content.Context
import androidx.lifecycle.ViewModel
import java.lang.Exception

/**
 * Main [ViewModel] in Project.
 *
 * It needs to run [prepare] function before first use
 */
class MainViewModel : ViewModel() {
    lateinit var preferences: UserPreferences

    /** It is used as constructor to view model */
    fun prepare(context: Context): Boolean {
        try {
            preferences = UserPreferences(context)
        } catch (e: Exception){
            return false
        }
        return true
    }
}