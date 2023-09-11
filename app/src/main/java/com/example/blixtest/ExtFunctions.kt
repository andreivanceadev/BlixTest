package com.example.blixtest

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toPrettyString(): String {
    val sdf = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

fun String.toDate(): Date {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.parse(this)!!
}