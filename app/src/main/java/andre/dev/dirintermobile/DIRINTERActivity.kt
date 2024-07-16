package andre.dev.dirintermobile

import andre.dev.campus.CampusFeature
import andre.dev.news.NewsFeature
import andre.dev.news.ui.theme.DIRINTERMobileTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.Locale

class DIRINTERActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DIRINTERMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val items = listOf("News", "Campus", "Dashboard")
                    var selectedItem by remember { mutableStateOf(0) }

                    Scaffold(
                        bottomBar = {
                            BottomAppBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        icon = {
                                            when (item) {
                                                "News" -> Icon(Icons.Filled.Home, contentDescription = "News")
                                                "Campus" -> Icon(Icons.Filled.LocationOn, contentDescription = "Campus")
                                                "Dashboard" -> Icon(Icons.Filled.Notifications, contentDescription = "Dashboard")
                                                else -> Icon(Icons.Filled.Warning, contentDescription = "Unknown")
                                            }
                                        },
                                        label = { Text(item) },
                                        selected = selectedItem == index,
                                        onClick = {
                                            selectedItem = index
                                            navController.navigate(item.lowercase(Locale.ROOT)) {
                                                popUpTo(navController.graph.startDestinationId)
                                                launchSingleTop = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "news",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("news") { NewsFeature() }
                            composable("campus") { CampusFeature() }
                            composable("dashboard") { Text("Dashboard Content") }
                        }
                    }
                }
            }
        }
    }
}
