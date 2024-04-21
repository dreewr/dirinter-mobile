package andre.dev.news

import andre.dev.lib.ViewModelFactory
import andre.dev.ui.LoginDependencyProvider
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginDependencyProviderImpl @Inject constructor(
    private val viewModelFactory: ViewModelFactory
) : LoginDependencyProvider {
    override fun <T : ViewModel> getViewModel(modelClass: Class<T>): T =
        viewModelFactory.create(modelClass)
}