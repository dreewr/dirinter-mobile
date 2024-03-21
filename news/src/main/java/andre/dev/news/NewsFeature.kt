package andre.dev.news

import andre.dev.ui.NewsScreen
import androidx.compose.runtime.Composable
@Composable
fun NewsFeature() {
    NewsScreen(
        NewsModuleInitializer.getNewsComponent().getViewModelProviderFactory()
    )
}
