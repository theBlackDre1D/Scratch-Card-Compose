package co.init.scratchcardcompose.features.card_scratch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.init.scratchcardcompose.R
import co.init.scratchcardcompose.ScratchCardSharedVM
import co.init.scratchcardcompose.extensions.safe

@Composable
fun CardScratchScreen(
    scratchCardSharedVM: ScratchCardSharedVM
) {
    val state = scratchCardSharedVM.scratchCardState

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (state.value?.loading.safe()) {
                CircularProgressIndicator(
                    modifier = Modifier.wrapContentSize()
                )
            }

            Text(text = stringResource(R.string.scratch_card__card_number_header))

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = state.value?.card?.cardNumber.orEmpty())
        }

        Button(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter),
            onClick = {
                scratchCardSharedVM.scratchCard()
            },
            enabled = !state.value?.card?.isScratched.safe()
        ) {
            Text(text = stringResource(R.string.scratch_card__scratch_card_button_text))
        }
    }
}