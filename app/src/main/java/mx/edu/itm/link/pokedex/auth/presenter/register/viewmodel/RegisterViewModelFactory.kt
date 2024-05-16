package mx.edu.itm.link.pokedex.auth.presenter.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignUp
import mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel.LoginViewModel

class RegisterViewModelFactory(
    private val signUpUseCase:SignUp
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(signUpUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}