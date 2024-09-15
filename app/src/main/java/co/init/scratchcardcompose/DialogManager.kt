package co.init.scratchcardcompose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties

object DialogManager {

    @Composable
    fun SuccessOkAlertDialog(message: String, onDismiss: (() -> Unit)? = null) {
        OkAlertDialog(
            title = stringResource(R.string.common__success),
            message = message,
            onDismiss = onDismiss
        )
    }

    @Composable
    fun ErrorOkAlertDialog(message: String, onDismiss: (() -> Unit)? = null) {
        OkAlertDialog(
            title = stringResource(R.string.common__error),
            message = message,
            onDismiss = onDismiss
        )
    }

    @Composable
    fun OkAlertDialog(title: String, message: String, onDismiss: (() -> Unit)? = null) {
        AlertDialog(
            onDismissRequest = {
                onDismiss?.invoke()
            },
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = { // 6
                Button(
                    onClick = {
                        onDismiss?.invoke()
                    }
                ) {
                    Text(text = stringResource(R.string.common__ok))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}