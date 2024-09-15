package co.init.scratchcardcompose.extensions

import android.view.View

fun Boolean?.safe(): Boolean = this == true
fun Boolean?.orFalse(): Boolean = false
fun Boolean?.orTrue(): Boolean = true

fun Boolean.toVisibleGone() = if (this) {
    View.VISIBLE
} else {
    View.GONE
}