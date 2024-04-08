package andre.dev.dirintermobile

import andre.dev.news.NewsFeature
import andre.dev.news.ui.theme.DIRINTERMobileTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class DIRINTERActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DIRINTERMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val items = listOf("Home", "Dashboard", "Notifications")
                    var selectedItem by remember { mutableStateOf(0) }

                    Scaffold(
                        bottomBar = {
                            BottomAppBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                Icons.Filled.Home,
                                                contentDescription = null
                                            )
                                        },
                                        label = { Text(item) },
                                        selected = selectedItem == index,
                                        onClick = { selectedItem = index }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        val currentNavController = rememberNavController()
                        NavHost(
                            navController = currentNavController,
                            startDestination = "main",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("main") { NewsFeature() }
                        }
                    }
                }
            }
        }
    }
}
