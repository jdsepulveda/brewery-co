package quebec.artm.breweryco.presentation.breweries.screens.brewerydetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quebec.artm.breweryco.R
import quebec.artm.breweryco.presentation.breweries.screens.landing.models.BreweryUiData

@Composable
fun BreweryDetailScreen(
    brewery: BreweryUiData,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.brewery_detail),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(40.dp))
        BreweryRow(text = stringResource(R.string.name, brewery.name))
        Spacer(Modifier.height(8.dp))
        BreweryRow(text = stringResource(R.string.address,brewery.address1))
        Spacer(Modifier.height(8.dp))
        BreweryRow(text = stringResource(R.string.type,brewery.type.name))
        Spacer(Modifier.height(8.dp))
        BreweryRow(text = stringResource(R.string.phone,brewery.phone))
        Spacer(Modifier.height(8.dp))
        BreweryRow(text = stringResource(R.string.website,brewery.websiteUrl))
        Spacer(Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onBackClick) {
                Text(text = stringResource(R.string.back_brewery_list))
            }
        }
    }
}

@Composable
fun BreweryRow(
    text: String
) {
    Text(
        text = text,
        textAlign = TextAlign.Start
    )
}