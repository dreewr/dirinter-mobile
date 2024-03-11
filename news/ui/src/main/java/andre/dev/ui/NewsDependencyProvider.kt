package andre.dev.ui

import androidx.lifecycle.ViewModelProvider

interface NewsDependencyProvider {
    fun getViewModelFactory(): ViewModelProvider.Factory
}