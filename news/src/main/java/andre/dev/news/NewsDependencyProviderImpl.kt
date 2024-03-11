package andre.dev.news

import andre.dev.ui.NewsDependencyProvider
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class NewsDependencyProviderImpl @Inject constructor(
    private val viewModelFactory: ViewModelFactory
): NewsDependencyProvider {
    override fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory
}