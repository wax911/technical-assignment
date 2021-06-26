package za.co.itschools.dev.core.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat

/**
 * Extension for getting system services
 */
inline fun <reified T> Context.systemServiceOf(): T? =
    ContextCompat.getSystemService(this, T::class.java)

fun Context.layoutInflater(): LayoutInflater =
    systemServiceOf<LayoutInflater>()!!

fun View.layoutInflater(): LayoutInflater =
    context.layoutInflater()