package mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignIn

class LoginViewModelFactory (
    private val signInUseCase:SignIn
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(signInUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}