package com.example.mandamax

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.mandamax.data.models.LogUsuario
import com.example.mandamax.repository.UserRepository

class MainActivityViewModel(private val userRepository: UserRepository?):ViewModel() {
     var usuarioLogeado:MutableLiveData<LogUsuario>

    init {
        usuarioLogeado= MutableLiveData()

    }

    fun getUser(){
        userRepository?.getUser(Observer {
            Log.d("Mensaje","")
            if(it!=null)
                usuarioLogeado.postValue(it as LogUsuario)

            })
    }

}