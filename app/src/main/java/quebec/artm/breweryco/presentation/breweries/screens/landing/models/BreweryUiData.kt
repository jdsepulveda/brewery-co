package quebec.artm.breweryco.presentation.breweries.screens.landing.models

import kotlinx.serialization.Serializable
import quebec.artm.breweryco.domain.breweries.model.BreweryType

@Serializable
data class BreweryUiData(
    val key: String,
    val name: String,
    val address1: String,
    val type: BreweryType,
    val phone: String,
    val websiteUrl: String
)