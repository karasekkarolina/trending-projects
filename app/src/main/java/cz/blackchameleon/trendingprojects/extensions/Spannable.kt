package cz.blackchameleon.trendingprojects.extensions

import android.text.Spannable
import android.text.TextPaint
import android.text.style.UnderlineSpan

/**
 * Extension function that removes underlines from hypertext
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
fun Spannable.removeUnderlines(): Spannable = apply {
    setSpan(
        object : UnderlineSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.isUnderlineText = false
            }
        }, 0, length, 0
    )
}