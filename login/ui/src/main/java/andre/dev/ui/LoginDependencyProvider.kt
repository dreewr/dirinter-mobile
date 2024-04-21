package andre.dev.ui

import androidx.lifecycle.ViewModel

interface LoginDependencyProvider {
    fun <T : ViewModel> getViewModel(modelClass: Class<T>): T
}