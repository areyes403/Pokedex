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
    private val singInUseCase:SignIn,
    private val getLocalUser:GetLocalUser,
    private val saveLocalUserUseCase:SaveLocalUser
):ViewModel() {
    private val _login=MutableLiveData<ResponseStatus<User>>()
    val login:LiveData<ResponseStatus<User>> get() = _login

    private val _user=MutableLiveData<User?>()
    val user:LiveData<User?> get() = _user

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch{
        _user.postValue(getLocalUser.invoke())
    }

    fun login(data:Credentials)=viewModelScope.launch(Dispatchers.IO){
        _login.postValue(singInUseCase.invoke(cred = data))
    }

    fun saveUser(user:User)=viewModelScope.launch {
        saveLocalUserUseCase(data = user)
    }

}