package quebec.artm.breweryco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import quebec.artm.breweryco.presentation.breweries.screens.brewerydetail.BreweryDetailScreen
import quebec.artm.breweryco.presentation.breweries.screens.landing.BreweriesScreen
import quebec.artm.breweryco.presentation.navigation.Destinations
import quebec.artm.breweryco.ui.theme.BreweryCoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BreweryCoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController,
                        startDestination = Destinations.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Destinations.Home> {
                            BreweriesScreen(
                                vm = hiltViewModel(),
                                onBreweryClick = { brewery ->
                                    navController.navigate(
                                        Destinations.BreweryDetail.createRoute(brewery)
                                    )
                                }
                            )
                        }
                        composable(
                            route = Destinations.BreweryDetail.ROUTE,
                            arguments = listOf(navArgument("brewery") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val brewery = Destinations.BreweryDetail.parse(backStackEntry)
                            if (brewery != null) {
                                BreweryDetailScreen(
                                    brewery = brewery,
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BreweryCoTheme {
        Greeting("Android")
    }
}