package com.example.marcin.mypodcasts.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

/**
 * Created by marci on 2017-10-13.
 */
class UsernameEditText @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : EditText(context, attrs, defStyle) {

    /* private var hasError = false

     fun validateUsername(): String? {
         if (validateEmptyValue()) return context.getString(R.string.this_field_cannot_be_empty)
         if (validateEmailPattern()) return context.getString(R.string.this_is_not_an_email)
         else return null
     }

     private fun validateEmailPattern(): Boolean {
         if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
             hasError = true
         }
         return hasError
     }

     private fun validateEmptyValue(): Boolean {
         if (text.isEmpty()) {
             hasError = true
         }
         return hasError
     }
 */
}