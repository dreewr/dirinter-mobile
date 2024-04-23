package andre.dev.campus

import andre.dev.campus.CampusModuleInitializer.getNewsComponent
import andre.dev.ui.CampusAction
import andre.dev.ui.CampusScreen
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun CampusFeature() {

    val navController = rememberNavController()
    val viewModelProviderFactory = getNewsComponent().getViewModelProviderFactory()

    Scaffold(
        topBar = {
           CampusToolbar(navController = navController)
        }
    ) { paddingValues->

        NavHost(
            navController = navController,
            startDestination = "newsList",
            modifier = Modifier.padding(paddingValues)) {
            composable("newsList") {
                CampusScreen(
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusToolbar(navController: NavController) {
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