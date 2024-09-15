package co.init.scratchcardcompose.navigation

import java.io.Serializable

abstract class Path(open val path: String)

sealed class CardHomeNavigation(override val path: String) : Path(path), Serializable {
    class Home : CardHomeNavigation("home")
    class ScratchCard : CardHomeNavigation("scratch")
    class ActivateCard : CardHomeNavigation("activation")
}