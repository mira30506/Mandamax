package com.example.mandamax.iu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.mandamax.data.models.LogUsuario
import com.example.mandamax.data.models.ResponseUsuario
import com.example.mandamax.data.models.Usuario
import com.example.mandamax.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {
     var susefullResponseLogin:MutableLiveData<ResponseUsuario>
     var failureResponseLogin:MutableLiveData<String>

    init {
        susefullResponseLogin=MutableLiveData()
        failureResponseLogin= MutableLiveData()
    }

    fun getLoginResponse( nombre:String,  password:String,mac:String, fecha:String ){
        return userRepository.login(nombre,password,mac,fecha, Observer {
            if(it is ResponseUsuario){
                susefullResponseLogin.postValue(it)
            }
            else if(it is String){
                failureResponseLogin.postValue(it)
            }
        })
    }

}