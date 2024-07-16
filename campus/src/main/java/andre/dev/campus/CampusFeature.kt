package andre.dev.campus

import andre.dev.campus.CampusModuleInitializer.getNewsComponent
import andre.dev.ui.CampusAction
import andre.dev.ui.CampusDetailsScreen
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun CampusFeature() {

    val navController = rememberNavController()
    val viewModelProviderFactory = getNewsComponent().getViewModelProviderFactory()

    Scaffold(topBar = {
        CampusToolbar(navController = navController)
    }) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "campusList",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("campusList") {
                CampusScreen(viewModelProvider = viewModelProviderFactory, onAction = { action ->
                    when (action) {
                        is CampusAction.CampusSelected -> navController.navigate(
                            "campusDetail/${action.id}/${action.campusName}"
                        )
                    }
                })
            }

            composable(route = "campusDetail/{id}/{name}",
                arguments = listOf(navArgument("id") { type = NavType.StringType },
                    navArgument("name") { type = NavType.StringType })) { backStackEntry ->
                CampusDetailsScreen(
                    campusId = backStackEntry.arguments?.getString("id") ?: String(),
                    viewModelProvider = viewModelProviderFactory
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

    TopAppBar(title = {
        Text(
            text = when (currentRoute) {
                "campusList" -> "Selecione um campus"
                "campusDetail/{id}/{name}" -> ("Campus "+  currentBackStackEntry?.arguments?.getString(
                    "name"
                ))
                else -> String()
            }
        )
    }, navigationIcon = {
        if (navController.previousBackStackEntry != null) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    })
}