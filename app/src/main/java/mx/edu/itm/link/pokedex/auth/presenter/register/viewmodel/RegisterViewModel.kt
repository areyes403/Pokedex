package mx.edu.itm.link.pokedex.auth.presenter.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignUp
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus

class RegisterViewModel(
    private val signUpUseCase:SignUp
):ViewModel() {
    private val _register=MutableLiveData<ResponseStatus<String>>()
    val register:LiveData<ResponseStatus<String>> get() = _register

    fun register(credentialData:Credentials,userData: User) = viewModelScope.launch(Dispatchers.IO){
        _register.postValue(ResponseStatus.Loading)
        _register.postValue(signUpUseCase.invoke(cred = credentialData, usr = userData))
    }
}