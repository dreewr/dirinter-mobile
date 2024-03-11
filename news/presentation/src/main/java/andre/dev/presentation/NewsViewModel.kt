package andre.dev.presentation

import andre.dev.news.domain.DoSomething
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NewsViewModel @Inject constructor(
val useCase: DoSomething
): ViewModel()