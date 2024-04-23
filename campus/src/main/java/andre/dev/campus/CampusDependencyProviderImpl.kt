package andre.dev.campus

import andre.dev.lib.ViewModelFactory
import andre.dev.ui.CampusDependencyProvider
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CampusDependencyProviderImpl @Inject constructor(
    private val viewModelFactory: ViewModelFactory
) : CampusDependencyProvider {
    override fun <T : ViewModel> getViewModel(modelClass: Class<T>): T =
        viewModelFactory.create(modelClass)
}