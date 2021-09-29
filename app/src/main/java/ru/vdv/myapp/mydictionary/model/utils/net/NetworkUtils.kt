package ru.vdv.myapp.mydictionary.model.utils.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo: NetworkInfo?
    netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}