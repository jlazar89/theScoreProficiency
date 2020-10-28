package com.thescore.proficiencyexercise.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created Date: 10/27/2020
 * Purpose: This object will contains all the utility methods needed to the specific app.
 */
object AppUtils {

    /**
     * Created Date: 10/27/2020
    * Purpose: Function to check network connection.
    */
    fun hasNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected)
    }
}