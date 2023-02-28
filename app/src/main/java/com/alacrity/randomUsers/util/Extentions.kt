package com.alacrity.randomUsers.util

import android.content.Context
import android.widget.Toast
import com.alacrity.randomUsers.R


fun validateAmountString(context: Context, text: String, block: (Int) -> Unit) {
    try {
        val amount = text.toInt()
        if (amount > 99) {
            Toast.makeText(context, context.getString(R.string.number_restriction), Toast.LENGTH_LONG).show()
            return
        }
        block(amount)
    } catch (e: Exception) {
        Toast.makeText(context, context.getString(R.string.enter_valid_number), Toast.LENGTH_LONG).show()
    }
}