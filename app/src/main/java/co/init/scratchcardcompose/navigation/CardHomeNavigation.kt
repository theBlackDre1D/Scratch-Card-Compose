package co.init.scratchcardcompose.navigation

abstract class Path(open val path: String)

sealed class CardHomeNavigation(override val path: String) : Path(path) {
    data object Home : CardHomeNavigation("home")
    data object ScratchCard : CardHomeNavigation("scratch")
    data object ActivateCard : CardHomeNavigation("activation")
}