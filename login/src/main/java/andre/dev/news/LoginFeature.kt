package andre.dev.news

import andre.dev.news.LoginModuleInitializer.getComponent
import andre.dev.ui.LoginAction
import andre.dev.ui.LoginScreen
import android.util.Log
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
fun LoginFeature() {

    val navController = rememberNavController()
    val viewModelProviderFactory = getComponent().getViewModelProviderFactory()

    Scaffold(
        topBar = {
            NewsToolbar(navController = navController)
        }
    ) { paddingValues->

        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(paddingValues)) {
            composable("login") {
                LoginScreen(
                    "Faça o login!",
                    viewModelProvider = viewModelProviderFactory,
                    onAction = { action ->
                        when (action) {
                            is LoginAction.OnLoginSuccessful -> {
                                Log.d("TESTE", "TESTE")//navController.navigate("newsDetail/${action.id}")
                            }
                        }
                    }
                )
            }
        }
    }
}

//todo: role da toolbar da feature
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