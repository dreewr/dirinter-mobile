package andre.dev.news

import andre.dev.ui.NewsDependencyProvider
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NewsDependencyProviderImpl @Inject constructor(
    private val viewModelFactory: ViewModelFactory
) : NewsDependencyProvider {
    override fun <T : ViewModel> getViewModel(modelClass: Class<T>): T =
        viewModelFactory.create(modelClass)
}