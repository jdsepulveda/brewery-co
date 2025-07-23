package quebec.artm.breweryco.presentation.breweries.screens.landing.models

sealed class BreweriesScreenViewModelState<out T> {
    data object Idle : BreweriesScreenViewModelState<Nothing>()
    data object Loading : BreweriesScreenViewModelState<Nothing>()
    data class Success<T>(val data: T) : BreweriesScreenViewModelState<T>()
    data class Error(val message: String) : BreweriesScreenViewModelState<Nothing>()
}