package co.init.scratchcardcompose.features.card_home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import co.init.scratchcardcompose.R
import co.init.scratchcardcompose.extensions.navigateToPath
import co.init.scratchcardcompose.features.ScratchCardSharedVM
import co.init.scratchcardcompose.navigation.CardHomeNavigation

@Composable
fun CardHomeScreen(navController: NavHostController, scratchCardSharedVM: ScratchCardSharedVM) {
    val state = scratchCardSharedVM.scratchCardState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

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

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navController.navigateToPath(CardHomeNavigation.ScratchCard)
                },
            ) {
                Text(text = stringResource(R.string.card_home__scratch_card))
            }

            Button(
                onClick = {
                    navController.navigateToPath(CardHomeNavigation.ActivateCard)
                },
            ) {
                Text(text = stringResource(R.string.card_home__activation_card))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    CardHomeScreen(navController)
}