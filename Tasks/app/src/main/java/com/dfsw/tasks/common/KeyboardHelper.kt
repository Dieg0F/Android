package com.dfsw.tasks.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.jetbrains.anko.runOnUiThread

object KeyboardHelper {

    fun hide(context: Context, view: View) {
        context.runOnUiThread {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager

            imm.hideSoftInputFromWindow(view.windowToken, 0);
        }
    }
}