package andre.dev.derintmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import andre.dev.news.ui.theme.DerintMobileTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            DerintMobileTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val items = listOf("Home", "Dashboard", "Notifications")
//                    var selectedItem by remember { mutableStateOf(0) }
//
//                    Scaffold(
//                        bottomBar = {
//                            BottomAppBar {
//                                items.forEachIndexed { index, item ->
//                                    NavigationBarItem(
//                                        icon = { Icon(Icons.Filled.Home, contentDescription = null) },
//                                        label = { Text(item) },
//                                        selected = selectedItem == index,
//                                        onClick = { selectedItem = index }
//                                    )
//                                }
//                            }
//                        }
//                    ) { innerPadding ->
//                        val currentNavController = rememberNavController()// navControllers[selectedItem]
//                        NavHost(
//                            navController = currentNavController,
//                            startDestination = "main",
//                            modifier = Modifier.padding(innerPadding)
//                        ) {
//                            //composable("main") { Text(text = "Main Screen of ${items[selectedItem]}") }
//                            // Define additional destinations here
//                        }
//                    }
//                }
//            }
//        }
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
    DerintMobileTheme {
        Greeting("Android")
    }
}


