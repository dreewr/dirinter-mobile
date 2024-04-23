package andre.dev.news.domain

import andre.dev.news.domain.interactor.ExecuteLoginUseCase
import andre.dev.news.domain.interactor.GetLoggedUserUseCase


interface LoginRepository: ExecuteLoginUseCase, GetLoggedUserUseCase