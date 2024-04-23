package andre.dev.campus

import andre.dev.campus.CampusModuleInitializer.getNewsComponent
import andre.dev.ui.CampusAction
import andre.dev.ui.NewsDetailsScreen
import andre.dev.ui.NewsScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun NewsFeature() {

    val navController = rememberNavController()
    val viewModelProviderFactory = getNewsComponent().getViewModelProviderFactory()

    Scaffold(
        topBar = {
            NewsToolbar(navController = navController)
        }
    ) { paddingValues->

        NavHost(
            navController = navController,
            startDestination = "newsList",
            modifier = Modifier.padding(paddingValues)) {
            composable("newsList") {
                NewsScreen(
                    viewModelProvider = viewModelProviderFactory,
                    onAction = { action ->
                        when (action) {
                            is CampusAction.OnCampusSelected -> {
                                navController.navigate("newsDetail/${action.id}")
                            }
                        }
                    }
                )
            }

            composable(
                route = "newsDetail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                // Retrieve the newsId from the backStackEntry
                val newsId = backStackEntry.arguments?.getString("id")
                newsId?.let {
                    NewsDetailsScreen(
                        newsId = it,
                        viewModelProvider = viewModelProviderFactory
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsToolbar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Text(text = when (currentRoute) {
                "newsList" -> "DIRINTER Mobile"
                else -> String()
            })
        },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {

                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }

            }
        }
    )
}