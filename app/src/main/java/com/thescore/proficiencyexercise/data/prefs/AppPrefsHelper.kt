package com.thescore.proficiencyexercise.data.prefs

import android.content.Context
import androidx.core.content.edit
import java.lang.RuntimeException

/**
* Created By: dev1618
* Created Date: 6/19/2019
* Purpose: Class to manage app level preference to store/retrieve data for application
 * from shared preference.
*/

class AppPrefsHelper(context: Context) {

    companion object {
        private const val PREF_NAME = "prefs_v1"
    }

    // Instance of shared preference.
    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    /**
     *  We prepared a generic function which can helpful to add the data to preference.
     */
    fun <T> putValue(key: String, value: T) {

        sharedPreferences.edit {
            when(value) {
                is String -> this.putString(key, value)
                is Boolean -> this.putBoolean(key, value)
                is Long -> this.putLong(key, value)
                is Int -> this.putInt(key, value)
                else -> throw RuntimeException("No Data Type Supported for shared Preference.")
            }
        }
    }

    // Function get the string from preference
    fun getString(key: String, fallback: String = ""): String = sharedPreferences.getString(key, fallback)?:fallback

    // Function get the Boolean from preference
    fun getBoolean(key: String, fallback: Boolean = false): Boolean = sharedPreferences.getBoolean(key, fallback)

    // Function get the int from preference
    fun getInt(key: String, fallback: Int = 0): Int = sharedPreferences.getInt(key, fallback)

    // Function get the long from preference
    fun getLong(key: String, fallback: Long = 0L): Long = sharedPreferences.getLong(key, fallback)

    // Function to remove specific key from shared preference
    fun removeValue(key: String) = sharedPreferences.edit().remove(key).apply()

    // Function to remove all the keys and clear preference.
    fun removeAll() = sharedPreferences.edit().clear().apply()

    // Check does specific key is available in preference or not.
    fun contains(key: String): Boolean = sharedPreferences.contains(key)



}