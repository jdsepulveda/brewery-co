package quebec.artm.breweryco.presentation.navigation

import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import quebec.artm.breweryco.presentation.breweries.screens.landing.models.BreweryUiData
import java.net.URLDecoder
import java.net.URLEncoder

object Destinations {
    @Serializable
    object Home

    @Serializable
    data class BreweryDetail(val brewery: BreweryUiData) {
        companion object {
            const val ROUTE = "breweryDetail/{brewery}"

            fun createRoute(brewery: BreweryUiData): String {
                val json = Json.encodeToString(brewery)
                val encoded = URLEncoder.encode(json, "UTF-8")
                return "breweryDetail/$encoded"
            }

            fun parse(backStackEntry: NavBackStackEntry): BreweryUiData? {
                val encoded = backStackEntry.arguments?.getString("brewery") ?: return null
                val decoded = URLDecoder.decode(encoded, "UTF-8")
                return Json.decodeFromString(decoded)
            }
        }
    }
}