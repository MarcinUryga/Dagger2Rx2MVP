package com.example.marcin.mypodcasts.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by marci on 2017-10-13.
 */
object KeyboardUtils {

    fun hide(view: View?) {
        view?.let {
            (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}