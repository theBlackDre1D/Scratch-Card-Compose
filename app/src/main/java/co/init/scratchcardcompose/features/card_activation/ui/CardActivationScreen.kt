package co.init.scratchcardcompose.features.card_activation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.init.scratchcardcompose.DialogManager
import co.init.scratchcardcompose.R
import co.init.scratchcardcompose.ScratchCardSharedVM
import co.init.scratchcardcompose.errorManager.ErrorManager
import co.init.scratchcardcompose.extensions.safe

@Composable
fun CardActivationScreen(
    scratchCardSharedVM: ScratchCardSharedVM
) {

    val state = scratchCardSharedVM.scratchCardState
    val activateCardState = scratchCardSharedVM.activateCardResult

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        activateCardState.value?.let { activationResult ->
            activationResult.fold(
                onSuccess = {
                    DialogManager.SuccessOkAlertDialog(stringResource(R.string.activation_card__activation_success)) {
                        scratchCardSharedVM.resetActivationResult()
                    }
                },
                onFailure = { throwable ->
                    val errorText = ErrorManager.getMessageFromThrowable(LocalContext.current, throwable)
                    errorText?.let {
                        DialogManager.ErrorOkAlertDialog(it) {
                            scratchCardSharedVM.resetActivationResult()
                        }
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(),
                text = stringResource(R.string.card_home__scratch_card_state),
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(),
                text = state.value?.card?.cardState.toString(),
                fontSize = 16.sp
            )
        }

        Button(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter),
            onClick = {
                scratchCardSharedVM.activateCard()
            },
            enabled = !state.value?.card?.isActivated.safe()
        ) {
            Text(text = stringResource(R.string.activation_card__activate_card))
        }
    }
}