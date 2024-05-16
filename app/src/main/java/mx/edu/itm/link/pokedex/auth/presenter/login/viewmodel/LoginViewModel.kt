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

class LoginViewModel(
    private val singInUseCase:SignIn
):ViewModel() {
    private val _login=MutableLiveData<ResponseStatus<Unit>>()
    val login:LiveData<ResponseStatus<Unit>> get() = _login

    fun login(data:Credentials)=viewModelScope.launch(Dispatchers.IO){
        _login.postValue(singInUseCase.invoke(cred = data))
    }

}