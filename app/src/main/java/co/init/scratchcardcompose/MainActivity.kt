package co.init.scratchcardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.init.scratchcardcompose.features.card_activation.ui.CardActivationScreen
import co.init.scratchcardcompose.features.card_home.CardHomeScreen
import co.init.scratchcardcompose.features.card_scratch.CardScratchScreen
import co.init.scratchcardcompose.navigation.CardHomeNavigation
import co.init.scratchcardcompose.ui.theme.ScratchCardComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val scratchCardSharedVM: ScratchCardSharedVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScratchCardComposeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                ) { _ ->
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = CardHomeNavigation.Home().path) {
                        composable(CardHomeNavigation.Home().path) { CardHomeScreen(navController, scratchCardSharedVM) }
                        composable(CardHomeNavigation.ScratchCard().path) { CardScratchScreen(scratchCardSharedVM) }
                        composable(CardHomeNavigation.ActivateCard().path) { CardActivationScreen(scratchCardSharedVM) }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        scratchCardSharedVM.cancelScratch()
    }
}