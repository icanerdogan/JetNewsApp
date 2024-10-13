package com.canerdogan.jetnews.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertDateFormat(dateString: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val date = formatter.parse(dateString) as Date

    // val outputFormatter = SimpleDateFormat("EEEE, MMM dd, yyyy 'at' hh:mm a 'UTC'")
    // Saturday, May 18, 2024 at 12:00 PM UTC

    val outputFormatter = SimpleDateFormat("dd MMM hh:mm", Locale.getDefault())
    return outputFormatter.format(date)
}