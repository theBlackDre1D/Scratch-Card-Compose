package co.init.scratchcardcompose.dialogManager

import android.app.AlertDialog
import android.content.Context
import co.init.scratchcardcompose.R

object DialogManager {

    fun showSuccessOkDialog(
        context: Context?,
        message: String?,
        onPositiveButtonClick: (() -> Unit)? = null
    ) {
        showOkDialog(
            context = context,
            title = context?.getString(R.string.common__success),
            message = message,
            onPositiveButtonClick = onPositiveButtonClick
        )
    }

    fun showErrorOkDialog(
        context: Context?,
        message: String?,
        onPositiveButtonClick: (() -> Unit)? = null
    ) {
        showOkDialog(
            context = context,
            title = context?.getString(R.string.common__error),
            message = message,
            onPositiveButtonClick = onPositiveButtonClick
        )
    }

    private fun showOkDialog(
        context: Context?,
        title: String?,
        message: String?,
        onPositiveButtonClick: (() -> Unit)? = null
    ) {
        context?.let { nonNullContext ->
            val builder = AlertDialog.Builder(nonNullContext)
            builder.setTitle(title)
            builder.setMessage(message)

            builder.setPositiveButton(nonNullContext.getText(R.string.common__ok)) { dialog, _ ->
                onPositiveButtonClick?.invoke()
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }
}