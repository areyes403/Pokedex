package mx.edu.itm.link.pokedex.user.presenter.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser
import mx.edu.itm.link.pokedex.user.domain.usecase.ObserveUser

class HomeViewModel(
    private val getLocalUserUseCase:GetLocalUser,
    private val observeUserUseCase:ObserveUser
):ViewModel() {

    private val _user=MutableStateFlow<ResponseStatus<User>>(ResponseStatus.Loading)
    val user:StateFlow<ResponseStatus<User>> get() = _user

    init {
        getUser()
    }

    private fun getUser() =viewModelScope.launch(Dispatchers.IO){
        val user=getLocalUserUseCase.invoke()!!
        observeUserUseCase.invoke(user.id).collect{
            _user.value=it
        }
    }
}