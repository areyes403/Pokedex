package mx.edu.itm.link.pokedex.user.presenter.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.auth.domain.usecase.LogOut
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser
import mx.edu.itm.link.pokedex.user.domain.usecase.ObserveUser

class HomeViewModelFactory(
    private val getLocalUserUseCase: GetLocalUser,
    private val observeUserUseCase: ObserveUser,
    private val logOutUseCase: LogOut
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(getLocalUserUseCase,observeUserUseCase,logOutUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}