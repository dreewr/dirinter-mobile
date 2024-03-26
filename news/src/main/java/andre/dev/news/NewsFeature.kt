package andre.dev.news

import andre.dev.news.NewsModuleInitializer.getNewsComponent
import andre.dev.ui.NewsAction
import andre.dev.ui.NewsDetailsScreen
import andre.dev.ui.NewsScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NewsFeature() {

    val navController = rememberNavController()
    val viewModelProviderFactory = getNewsComponent().getViewModelProviderFactory()

    NavHost(navController = navController, startDestination = "newsList") {
        composable("newsList") {
            NewsScreen(
                viewModelProvider = viewModelProviderFactory,
                onAction = { action ->
                    when (action) {
                        is NewsAction.OnNewsSelected -> {
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
                NewsDetailsScreen(newsId = it)
            }
        }
    }


}