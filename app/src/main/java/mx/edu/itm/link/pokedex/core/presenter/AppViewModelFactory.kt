package mx.edu.itm.link.pokedex.core.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignIn
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignUp
import mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel.LoginViewModel
import mx.edu.itm.link.pokedex.auth.presenter.register.viewmodel.RegisterViewModel
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser
import mx.edu.itm.link.pokedex.user.domain.usecase.ObserveUser
import mx.edu.itm.link.pokedex.user.domain.usecase.SaveLocalUser
import mx.edu.itm.link.pokedex.user.presenter.home.viewmodel.HomeViewModel

class AppViewModelFactory(
    private val signInUseCase: SignIn,
    private val signUpUseCase: SignUp,
    private val getLocalUserUseCase: GetLocalUser,
    private val observeUserUseCase: ObserveUser,
    private val saveLocalUser: SaveLocalUser
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(getLocalUserUseCase, observeUserUseCase) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(signInUseCase, getLocalUserUseCase, saveLocalUser) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(signUpUseCase) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }

        }
    }
}