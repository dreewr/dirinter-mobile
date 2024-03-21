package andre.dev.ui

import androidx.lifecycle.ViewModel
interface NewsDependencyProvider {
    fun <T : ViewModel> getViewModel(modelClass: Class<T>): T
}