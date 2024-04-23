package andre.dev.ui

import androidx.lifecycle.ViewModel
interface CampusDependencyProvider {
    fun <T : ViewModel> getViewModel(modelClass: Class<T>): T
}