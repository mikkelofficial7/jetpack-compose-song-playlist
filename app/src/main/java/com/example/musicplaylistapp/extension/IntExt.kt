package com.example.musicplaylistapp.extension

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun Int?.convertMillisToMinute() : String {
    if(this == null) return "00:00"

    val date = Date(this.toLong())
    val formatter: DateFormat = SimpleDateFormat("mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(date)
}