package com.example.sonika.pushnotificationapp.utils

import android.content.Context
import android.widget.Toast
import java.time.Duration

fun Context.getToastMessage(message : String, duration: Int = Toast.LENGTH_SHORT)
{
    Toast.makeText(this, message, duration).show()
}