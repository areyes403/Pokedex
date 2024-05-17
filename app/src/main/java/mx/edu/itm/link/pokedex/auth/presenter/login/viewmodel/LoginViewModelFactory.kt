package mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignIn
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser
import mx.edu.itm.link.pokedex.user.domain.usecase.SaveLocalUser

class LoginViewModelFactory (
    private val signInUseCase:SignIn,
    private val getLocalUserUseCse:GetLocalUser,
    private val saveLocalUser:SaveLocalUser
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(signInUseCase,getLocalUserUseCse,saveLocalUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}