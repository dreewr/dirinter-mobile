package andre.dev.news

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
import andre.dev.presentation.NewsViewModel
import andre.dev.ui.NewsScreen
import androidx.compose.runtime.ComposeCompilerApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DerintMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Facade()
                }
            }
        }
    }
}


@Composable
fun Facade(){
    NewsScreen(NewsModuleInitializer.getNewsComponent().getViewModelProviderFactory().create(NewsViewModel::class.java))
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


