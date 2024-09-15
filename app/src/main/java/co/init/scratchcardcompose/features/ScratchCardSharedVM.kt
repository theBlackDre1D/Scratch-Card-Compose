package co.init.scratchcardcompose.features

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import co.init.scratchcardcompose.data.Card
import co.init.scratchcardcompose.errorManager.throwables.CanNotActivateCardThrowable
import co.init.scratchcardcompose.extensions.doInCoroutine
import co.init.scratchcardcompose.extensions.doInIOCoroutine
import co.init.scratchcardcompose.extensions.safe
import co.init.scratchcardcompose.features.card_activation.domain.ActivateScratchedCardUseCase
import co.init.scratchcardcompose.features.card_activation.domain.ScratchCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject


@HiltViewModel
class ScratchCardSharedVM @Inject constructor(
    private val activateScratchedCardUseCase: ActivateScratchedCardUseCase,
    private val scratchedCardUseCase: ScratchCardUseCase
) : ViewModel() {

    data class ScratchCardState(
        val card: Card? = Card(),
        val loading: Boolean = false
    )

    private val _scratchCardState = mutableStateOf<ScratchCardState?>(ScratchCardState())
    val scratchCardState: State<ScratchCardState?> = _scratchCardState

    private val _activateCardResult = mutableStateOf<Result<Card>?>(null)
    val activateCardResult: State<Result<Card>?> = _activateCardResult

    private var scratchCardJob: Job? = null
    private var activationJob: Job? = null

    fun scratchCard() {
        if (scratchCardJob?.isActive.safe()) return

        scratchCardJob = doInCoroutine {
            _scratchCardState.value?.card?.let { card ->
                _scratchCardState.value = _scratchCardState.value?.copy(loading = true)

                scratchedCardUseCase(card).collect { result ->
                    _scratchCardState.value = _scratchCardState.value?.copy(
                        card = result.getOrNull(),
                        loading = false
                    )
                }
            }
        }
    }

    fun cancelScratch() {
        _scratchCardState.value = _scratchCardState.value?.copy(loading = false)
        scratchCardJob?.cancel()
    }

    fun resetActivationResult() {
        _activateCardResult.value = null
    }

    fun activateCard() {
        if (activationJob?.isActive.safe()) return

        activationJob = doInIOCoroutine {
            _scratchCardState.value?.card?.let { card ->
                if (card.canBeActivated) {
                    activateScratchedCardUseCase(card).collect { result ->
                        result.onSuccess { card ->
                            _scratchCardState.value = _scratchCardState.value?.copy(card = card)
                        }
                        _activateCardResult.value = result
                    }
                } else {
                    _activateCardResult.value = Result.failure(CanNotActivateCardThrowable())
                }
            }
        }
    }
}