package co.init.scratchcardcompose.extensions

import androidx.navigation.NavController
import co.init.scratchcardcompose.navigation.Path

fun NavController.navigateToPath(path: Path) {
    this.navigate(path.path)
}