package co.init.scratchcardcompose.extensions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import co.init.scratchcardcompose.navigation.Path

fun NavGraphBuilder.composablePath(
    path: Path,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
){
    this.composable(
        route = path.path,
        content = content
    )
}