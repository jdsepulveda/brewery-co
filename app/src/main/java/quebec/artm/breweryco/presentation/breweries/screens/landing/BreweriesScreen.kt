package quebec.artm.breweryco.presentation.breweries.screens.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import quebec.artm.breweryco.presentation.breweries.screens.landing.composables.ErrorMessage
import quebec.artm.breweryco.presentation.breweries.screens.landing.composables.FullScreenLoading
import quebec.artm.breweryco.presentation.breweries.screens.landing.models.BreweriesScreenViewModelState
import quebec.artm.breweryco.presentation.breweries.screens.landing.models.BreweryUiData

@Composable
fun BreweriesScreen(
    vm: BreweriesScreenViewModel,
    onBreweryClick: (BreweryUiData) -> Unit
) {
    val state by vm.state.collectAsState()

    if (state is BreweriesScreenViewModelState.Loading) {
        FullScreenLoading(
            modifier = Modifier.fillMaxSize()
        )
    }
    if (state is BreweriesScreenViewModelState.Error) {
        ErrorMessage(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            text = (state as BreweriesScreenViewModelState.Error).message
        )
    }
    if (state is BreweriesScreenViewModelState.Success) {
        val breweries = (state as BreweriesScreenViewModelState.Success).data

        BreweriesRender(
            modifier = Modifier.fillMaxWidth(),
            breweries = breweries,
            onBreweryClick = onBreweryClick
        )
    }
}

@Composable
private fun BreweriesRender(
    modifier: Modifier = Modifier,
    breweries: List<BreweryUiData>,
    onBreweryClick: (BreweryUiData) -> Unit
) {
    LazyColumn(modifier) {
        itemsIndexed(breweries, key = { _, item -> item.key }) { index, brewery ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBreweryClick(brewery) }
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                text = brewery.name
            )

            if (index < breweries.size - 1) {
                HorizontalDivider()
            }
        }
    }
}