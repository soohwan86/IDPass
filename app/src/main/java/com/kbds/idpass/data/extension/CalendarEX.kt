package com.kbds.idpass.data.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.getCurrentTime(format: String): String = SimpleDateFormat(format, Locale.KOREA).format(Calendar.getInstance().time)