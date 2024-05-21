package mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignIn
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser
import mx.edu.itm.link.pokedex.user.domain.usecase.SaveLocalUser

class LoginViewModel(
    private val signInUseCase:SignIn,
    private val getLocalUserUseCse:GetLocalUser
):ViewModel() {
    private val _login=MutableLiveData<ResponseStatus<Unit>>()
    val login:LiveData<ResponseStatus<Unit>> get() = _login

    private val _user=MutableLiveData<User?>()
    val user:LiveData<User?> get() = _user


    fun login(credential:Credentials)=viewModelScope.launch(Dispatchers.IO){
        _login.postValue(signInUseCase(data = credential)!!)
    }


}