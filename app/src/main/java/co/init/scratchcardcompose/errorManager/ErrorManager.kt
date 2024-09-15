package co.init.scratchcardcompose.errorManager

import android.content.Context
import co.init.scratchcardcompose.R
import co.init.scratchcardcompose.errorManager.throwables.CanNotActivateCardThrowable
import co.init.scratchcardcompose.errorManager.throwables.FailedActivationThrowable

object ErrorManager {

    fun getMessageFromThrowable(context: Context?, throwable: Throwable): String? {
        return when (throwable) {
            is CanNotActivateCardThrowable -> context?.getString(R.string.activation_card__activation_error_card_not_ready_to_activate)
            is FailedActivationThrowable -> context?.getString(R.string.activation_card__activation_error)
            else -> throwable.message
        }
    }
}