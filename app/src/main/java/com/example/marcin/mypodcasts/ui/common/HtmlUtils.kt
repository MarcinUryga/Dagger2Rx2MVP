package com.example.marcin.mypodcasts.ui.common

import android.os.Build
import android.text.Html
import android.text.Spanned

/**
 * Created by marci on 2018-05-18.
 */
object HtmlUtils {

  fun createHtml(html: String?): Spanned? {
    html ?: return null
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY) else Html.fromHtml(html)
  }
}